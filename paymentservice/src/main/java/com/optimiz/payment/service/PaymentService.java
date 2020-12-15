package com.optimiz.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.optimiz.payment.model.PaymentOrder;

/**
 * Facade for the payment service functionality
 *
 */
@Service
public class PaymentService {
	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
	
	@Value("${kafka.payment.topic.name}")
	private String paymentTopic;
	
	@Autowired
	KafkaTemplate<String, PaymentOrder> kafkaTemplate;
	
	public Boolean pay(PaymentOrder paymentOrder) {		
		log.info("Payment order is placed successfully.");
		return true;
	}
}
