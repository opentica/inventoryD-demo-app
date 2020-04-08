package com.optimiz.payment.controller;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(path="/pay", method=RequestMethod.POST)
	public ResponseEntity<Boolean> pay(@RequestBody PaymentOrder paymentOrder) throws SQLException {	
		log.info("Initiating a payment order : " + paymentOrder);
		if(!StringUtils.isBlank(paymentOrder.getProductId()) && paymentOrder.getProductId().equalsIgnoreCase("Z1")) {
			log.error("Payment order failed due ");
			throw new SQLException("Database connection failed");
		} if(!StringUtils.isBlank(paymentOrder.getProductId()) && paymentOrder.getProductId().equalsIgnoreCase("T1")) {
			try {
				//Adding sleep for simulating a case to wait for 3 more seconds in special condition 
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ResponseEntity.ok().body(paymentService.pay(paymentOrder));		
	}

	@RequestMapping(path="/test", method=RequestMethod.GET)
	public ResponseEntity<Boolean> test() {
		return ResponseEntity.ok().body(paymentService.pay(null));	
	}
}
