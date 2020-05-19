package com.opentica.inventory.app.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.opentica.inventory.app.bean.ProductPurchase;
import com.opentica.inventory.app.service.ProductPurchaseService;

//@Component
//@EnableAsync
//@EnableScheduling
public class ScheduledPurchaseTask {
	/*@Value("${inventory.service.purchase.url}")
	private String inventoryServicePurchaseUrl;

	private static final Logger log = LoggerFactory.getLogger(ScheduledPurchaseTask.class);

	private List<String> getProductList() {
		List<String> list = new ArrayList<>();
		list.add("Phone");
		list.add("Camera");
		list.add("Laptop");
		list.add("Coffee Machine");
		return list;
	}

	// 30 seconds delay
	@Scheduled(fixedDelay = 30000)
	public void scheduledPurchase() throws URISyntaxException {
		Random random = new Random();
		ProductPurchase productPurchase = new ProductPurchase();
		productPurchase.setCustomerId("C" + random.nextInt(999));
		productPurchase.setPrice(random.nextInt(999));
		productPurchase.setProductId("P" + random.nextInt(999));
		List<String> productList = getProductList();
		productPurchase.setProductName(productList.get(new Random().nextInt(productList.size())));
		log.info("Starting the product purchase : " + productPurchase);
		this.purchase(productPurchase);
	}
	// 30 seconds delay
	@Scheduled(fixedDelay = 30000)
	public void scheduledFailurePurchase() throws URISyntaxException {
		Random random = new Random();
		ProductPurchase productPurchase = new ProductPurchase();
		productPurchase.setCustomerId("C" + random.nextInt(999));
		productPurchase.setPrice(random.nextInt(999));
		// Z1 is failure code for the failure request
		productPurchase.setProductId("Z1");
		List<String> productList = getProductList();
		productPurchase.setProductName(productList.get(new Random().nextInt(productList.size())));
		log.info("Starting the product purchase for Z1 : " + productPurchase);
		this.purchase(productPurchase);
	}

	private void purchase(ProductPurchase product) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI payUrl = new URI(inventoryServicePurchaseUrl);
		ResponseEntity<Boolean> result = restTemplate.postForEntity(payUrl, product, Boolean.class);
		log.info("Product purchase order is placed for product " + product);ō
	}
	*/
}
