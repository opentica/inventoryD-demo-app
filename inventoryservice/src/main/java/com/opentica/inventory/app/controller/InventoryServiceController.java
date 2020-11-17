package com.opentica.inventory.app.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;
import com.opentica.inventory.app.bean.PaymentOrder;
import com.opentica.inventory.app.bean.ProductInfo;
import com.opentica.inventory.app.bean.ProductPurchase;
import com.opentica.inventory.app.bean.ProductRepository;
import com.opentica.inventory.app.helper.MessageProducerHelper;
import com.opentica.inventory.app.service.CustomerService;
import com.opentica.inventory.app.service.PaymentService;
import com.opentica.inventory.app.service.ProductPurchaseService;
import com.opentica.inventory.app.service.ProductService;

/**
 * Controller for CRUD operations on the product inventory
 *
 */
@RestController
@RequestMapping("/api/product/")
public class InventoryServiceController {
	private static final Logger log = LoggerFactory.getLogger(InventoryServiceController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MessageProducerHelper messageProducerHelper;
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductPurchaseService purchaseService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path="/") 
	public void getHealthCheck() {
		log.info("Health check is successful!");
	}
	
	/**
	 * API to get list of all available products
	 * 
	 * @return
	 */
	@GetMapping("/getProducts")
	public List<ProductInfo> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Add a product with the provided product details
	 * 
	 * @param productInfo
	 * @return
	 */
	@PostMapping("/addProduct")
	public ProductInfo addProduct(@Valid @RequestBody ProductInfo productInfo) {
		ProductInfo info =  productRepository.save(productInfo);
	    messageProducerHelper.sendProductInfo(info);
	    return info;
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/deleteProduct")
	public void deleteProuct(@Valid @RequestBody ProductInfo productInfo) {
		productRepository.delete(productInfo);
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		productRepository.deleteAll();
	}
	
	@RequestMapping(path="/purchase", method=RequestMethod.POST)
	public ResponseEntity<Boolean> purchase(@RequestBody ProductPurchase product) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		log.info("Product purchase started for product : " + product);
		if(StringUtils.isBlank(product.getCustomerId()) || StringUtils.isBlank(product.getProductId())) {
			log.error("Purchase order failed due to the invalid parameters");
			throw new IllegalArgumentException("Invalid product details are provided");
		}
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setPrice(product.getPrice());
		paymentOrder.setProductId(product.getProductId());
		try {
			stopwatch.stop();
			log.info("Time taken for product purchase API execution : " + stopwatch);
			return ResponseEntity.ok().body(purchaseService.purchase(product));
			
		} catch (URISyntaxException e) {
			//If the order fails
			log.error("Product purchase failed for product : " + product);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	@RequestMapping(path="/payment", method=RequestMethod.POST)
	public ResponseEntity<Boolean> payment(@RequestBody PaymentOrder paymentOrder) {
		log.info("Payment processing started");
		log.info("Initiating a payment order : " + paymentOrder);
		if(paymentOrder.getPrice() < 0 || StringUtils.isBlank(paymentOrder.getProductId())) {
			log.error("Payment order failed due to the invalid parameters");
			throw new IllegalArgumentException("Invalid payment and product details are provided");
		}
		try {
			return ResponseEntity.ok().body(paymentService.pay(paymentOrder));
		} catch (URISyntaxException e) {
			//If the order fails
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	/**
	 * Get all products from the catalog
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping(path="/listProductCatalog") 
	public ResponseEntity<String> getProducts() throws URISyntaxException {
		log.info("listProductCatalog API is invoked");
		return productService.getProducts();
	}
	
	/**
	 * Get all customers in the system
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping(path="/getCustomers") 
	public ResponseEntity<String> getCustomerList() throws URISyntaxException {
		return customerService.getCusomers();
	}	
}
