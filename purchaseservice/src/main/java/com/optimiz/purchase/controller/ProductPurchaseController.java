package com.optimiz.purchase.controller;

import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimiz.purchase.model.PaymentOrder;
import com.optimiz.purchase.model.ProductPurchase;
import com.optimiz.purchase.service.ProductPurchaseService;


/**
 * Controller for handling the Product return requests
 *
 */
@RestController
public class ProductPurchaseController {
	private static final Logger log = LoggerFactory.getLogger(ProductPurchaseController.class);

	@Autowired
	private ProductPurchaseService purchaseService;
	
	@RequestMapping(path="/purchase", method=RequestMethod.POST)
	public ResponseEntity<Boolean> purchase(@RequestParam ProductPurchase product) {
		log.info("Initiating a purchase order for order : " + product);
		if(!StringUtils.isBlank(product.getProductId()) && product.getProductId().equals("Z1")) {
			log.error("Purchase order failed due to the invalid parameters");
			throw new IllegalArgumentException("Invalid product details are provided");
		}
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setPayment(product.getPrice());
		paymentOrder.setProductId(product.getProductId());
		try {
			return ResponseEntity.ok().body(purchaseService.purchase(product, paymentOrder));
		} catch (URISyntaxException e) {
			//If the order fails
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}		
	}
}
