package com.opentica.inventory.app.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

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

import com.opentica.inventory.app.bean.PaymentOrder;
import com.opentica.inventory.app.bean.ProductInfo;
import com.opentica.inventory.app.bean.ProductPurchase;
import com.opentica.inventory.app.bean.ProductRepository;
import com.opentica.inventory.app.helper.MessageProducerHelper;
import com.opentica.inventory.app.service.PaymentService;
import com.opentica.inventory.app.service.ProductPurchaseService;

/**
 * Controller for CRUD operations on the product inventory
 *
 */
@RestController
@RequestMapping("/api/product/")
public class InventoryServiceController {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MessageProducerHelper messageProducerHelper;
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductPurchaseService purchaseService;

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
	public ResponseEntity<Boolean> purchase(ProductPurchase product) {
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setPayment(product.getPrice());
		paymentOrder.setProductId(product.getProductId());
		try {
			return ResponseEntity.ok().body(purchaseService.purchase(product));
		} catch (URISyntaxException e) {
			//If the order fails
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	@RequestMapping(path="/payment", method=RequestMethod.POST)
	public ResponseEntity<Boolean> payment(PaymentOrder order) {
		try {
			return ResponseEntity.ok().body(paymentService.pay(order));
		} catch (URISyntaxException e) {
			//If the order fails
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
}
