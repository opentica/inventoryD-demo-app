package com.opentica.inventory.app.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.opentica.inventory.app.bean.ProductInfo;

@Service
public class MessageProducerHelper {
	private static final Logger log = LoggerFactory.getLogger(MessageProducerHelper.class);

	@Value("${kafka.topic.name}")
	private String topicName;
	
	@Autowired
	KafkaTemplate<String, ProductInfo> kafkaTemplate;
	
	public void sendProductInfo(ProductInfo productInfo) {
		log.info("Sending product");
		kafkaTemplate.send(topicName, productInfo);
		log.info("Product info sent successfully");
	}
}
