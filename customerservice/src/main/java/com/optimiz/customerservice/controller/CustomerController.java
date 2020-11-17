package com.optimiz.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimiz.customerservice.model.Customer;

@RestController
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping(path="/") 
	public void getHealthCheck() {
		log.info("Health check is successful");
	}

	@GetMapping(path="/customerservice/getCustomers") 
	public List<Customer> getCustomerList() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer("Tom", 10001, "Canada"));
		customerList.add(new Customer("Andy", 10005, "Canada"));
		customerList.add(new Customer("Tina", 10006, "Canada"));
		return customerList;
	}
}
