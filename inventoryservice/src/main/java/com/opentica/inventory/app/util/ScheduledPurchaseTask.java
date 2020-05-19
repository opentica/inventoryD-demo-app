package com.opentica.inventory.app.util;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.opentica.inventory.app.bean.ProductPurchase;
import com.opentica.inventory.app.service.ProductPurchaseService;

@Component
@EnableAsync
@EnableScheduling
public class ScheduledPurchaseTask {
	@Autowired
	ProductPurchaseService purchaseService;

	private static final Logger log = LoggerFactory.getLogger(ProductPurchaseService.class);

	private List<String> getProductList() {
		List<String> list = new ArrayList<>();
		list.add("Phone");
		list.add("Camera");
		list.add("Laptop");
		list.add("Coffee Machine");
		return list;
	}
	/**
	 * Scheduled execution for the random product purchase scenario
	 * @throws URISyntaxException
	 */
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
		purchaseService.purchase(productPurchase);
	}

	/**
	 * Scheduled execution for the failure usecase scenario
	 * 
	 * @throws URISyntaxException
	 */
	// 30 seconds delay
	@Scheduled(fixedDelay = 30000)
	public void scheduledFailurePurchase() throws URISyntaxException {
		Random random = new Random();
		ProductPurchase productPurchase = new ProductPurchase();
		productPurchase.setCustomerId("C" + random.nextInt(999));
		productPurchase.setPrice(random.nextInt(999));
		//Z1 is failure code for the failure request
		productPurchase.setProductId("Z1");
		List<String> productList = getProductList();
		productPurchase.setProductName(productList.get(new Random().nextInt(productList.size())));
		log.info("Starting the product purchase for Z1 : " + productPurchase);
		purchaseService.purchase(productPurchase);
	}
}
