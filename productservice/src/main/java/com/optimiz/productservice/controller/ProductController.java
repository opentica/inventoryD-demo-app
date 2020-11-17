package com.optimiz.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimiz.productservice.model.Product;

@RestController
public class ProductController {	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping(path="/") 
	public void getHealthCheck() {
		log.info("Health check is successful");
	}
	
	@GetMapping(path="/productservice/getProducts") 
	public List<Product> getProducts() {
		log.info("invoking getProducts");
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("Pen", 10001, 10.5));
		productList.add(new Product("Shirt", 10001, 200.5));
		return productList;
	}
}
