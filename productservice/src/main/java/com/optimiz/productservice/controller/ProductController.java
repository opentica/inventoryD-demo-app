package com.optimiz.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimiz.productservice.model.Product;

@RestController
public class ProductController {
	@GetMapping(path="/getProducts") 
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("Pen", 10001, 10.5));
		productList.add(new Product("Shirt", 10001, 200.5));
		return productList;
	}
}
