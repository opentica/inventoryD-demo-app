package com.optimiz.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
	private String productName;
	
	private int productId;
	
	private double price;
}
