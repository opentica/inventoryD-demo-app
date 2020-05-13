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

@Service
public class ProductService {
	@Value("${product.service.url}")
	private String productServiceUrl;
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public ResponseEntity<String> getProducts() throws URISyntaxException {
		log.info("Getting product details.");
		RestTemplate restTemplate = new RestTemplate();
		URI productUrl = new URI(productServiceUrl);
		log.info("Invoking API..");
		ResponseEntity<String> result = restTemplate.getForEntity(productUrl, String.class);
		log.info("Got product details.");
		return result;
	}
}
