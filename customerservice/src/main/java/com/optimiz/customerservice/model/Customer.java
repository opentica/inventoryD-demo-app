package com.optimiz.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
	private String customerName;
	
	private int customerId;
	
	private String customerAddress;
}
