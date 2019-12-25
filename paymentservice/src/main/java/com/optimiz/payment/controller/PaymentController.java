package com.optimiz.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.optimiz.payment.model.PaymentOrder;
import com.optimiz.payment.service.PaymentService;


/**
 * Controller for handling the Payment related requests
 *
 */
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(path="/pay", method=RequestMethod.POST)
	public ResponseEntity<Boolean> pay(PaymentOrder paymentOrder) {
		return ResponseEntity.ok().body(paymentService.pay(paymentOrder));		
	}	
	
	@RequestMapping(path="/test", method=RequestMethod.GET)
	public ResponseEntity<Boolean> test() {
		return ResponseEntity.ok().body(paymentService.pay(null));	
	}	
}
