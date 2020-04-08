package com.optimiz.purchase.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.optimiz.purchase.model.PaymentOrder;
import com.optimiz.purchase.model.ProductPurchase;

/**
 * Facade for the product purchase functionality
 *
 */
@Service
public class ProductPurchaseService {
	@Value("${pay.service.url}")
	private String payServiceUrl;
	
	private static final Logger log = LoggerFactory.getLogger(ProductPurchaseService.class);

	public Boolean purchase(ProductPurchase product, PaymentOrder order) throws URISyntaxException {
		log.info("Product purchase order is placed for product : " + order);
		RestTemplate restTemplate = new RestTemplate();
		URI payUrl = new URI(payServiceUrl);
		ResponseEntity<Boolean> result = restTemplate.postForEntity(payUrl, order, Boolean.class);
		log.info("Product payment order is initiated for product : " + product);
		return result.getBody();
	}
}
