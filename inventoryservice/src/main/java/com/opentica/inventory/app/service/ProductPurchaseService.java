package com.opentica.inventory.app.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.opentica.inventory.app.bean.PaymentOrder;
import com.opentica.inventory.app.bean.ProductPurchase;

/**
 * Facade for the product purchase functionality
 *
 */
@Service
public class ProductPurchaseService {
	@Value("${purchase.service.url}")
	private String purchaseServiceUrl;
	
	private static final Logger log = LoggerFactory.getLogger(ProductPurchaseService.class);

	public Boolean purchase(ProductPurchase product) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI payUrl = new URI(purchaseServiceUrl);
		ResponseEntity<Boolean> result = restTemplate.postForEntity(payUrl, product, Boolean.class);
		log.info("Product purchase order is placed for product " + product);
		return result.getBody();
	}
}
