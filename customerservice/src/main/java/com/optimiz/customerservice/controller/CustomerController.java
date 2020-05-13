package com.optimiz.customerservice.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.optimiz.customerservice.model.Customer;

@RestController
public class CustomerController {
	@GetMapping(path="/getCustomers") 
	public List<Customer> getCustomerList() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer("Tom", 10001, "Canada"));
		customerList.add(new Customer("Andy", 10005, "Canada"));
		customerList.add(new Customer("Tina", 10006, "Canada"));
		return customerList;
	}
}
