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
public class PaymentService {
	@Value("${pay.service.url}")
	private String payServiceUrl;
	
	private static final Logger log = LoggerFactory.getLogger(ProductPurchaseService.class);

	public Boolean pay(PaymentOrder order) throws URISyntaxException {
		log.info("Product purchase order is placed.");
		RestTemplate restTemplate = new RestTemplate();
		URI payUrl = new URI(payServiceUrl);
		ResponseEntity<Boolean> result = restTemplate.postForEntity(payUrl, order, Boolean.class);
		log.info("Product paument order is placed.");
		return result.getBody();
	}
}
