package com.opentica.inventory.app.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
	@Value("${customer.service.url}")
	private String customerServiceUrl;
	
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	public ResponseEntity<String> getCusomers() throws URISyntaxException {
		log.info("Getting customer details.");
		RestTemplate restTemplate = new RestTemplate();
		URI customerUrl = new URI(customerServiceUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(customerUrl, String.class);
		log.info("Got customer details.");
		return result;
	}
}
