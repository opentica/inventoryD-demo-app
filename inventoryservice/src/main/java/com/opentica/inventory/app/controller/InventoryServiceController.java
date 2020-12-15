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
	@GetMapping("/api/product/getProducts")
	public List<ProductInfo> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Add a product with the provided product details
	 * 
	 * @param productInfo
	 * @return
	 */
	@PostMapping("/api/product/addProduct")
	public ProductInfo addProduct(@Valid @RequestBody ProductInfo productInfo) {
		ProductInfo info =  productRepository.save(productInfo);
	    return info;
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/api/product/deleteProduct")
	public void deleteProuct(@Valid @RequestBody ProductInfo productInfo) {
		productRepository.delete(productInfo);
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/api/product/deleteAll")
	public void deleteAll() {
		productRepository.deleteAll();
	}
	
	@RequestMapping(path="/api/product/purchase", method=RequestMethod.POST)
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
	
	@RequestMapping(path="/api/product/payment", method=RequestMethod.POST)
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
	@GetMapping(path="/api/product/listProductCatalog") 
	public ResponseEntity<String> getProducts() throws URISyntaxException {
		log.info("listProductCatalog API is invoked");
		ResponseEntity<String> response = productService.getProducts();
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
	/**
	 * Get all customers in the system
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping(path="/api/product/getCustomers") 
	public ResponseEntity<String> getCustomerList() throws URISyntaxException {
		ResponseEntity<String> response = customerService.getCusomers();
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}	
}
