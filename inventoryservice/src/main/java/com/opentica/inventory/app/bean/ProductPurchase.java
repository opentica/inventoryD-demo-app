package com.opentica.inventory.app.bean;

import lombok.Data;
import lombok.ToString;

/**
 * Product return details POJO
 *
 */
@Data
@ToString
public class ProductPurchase {
	private String productId;
	private String customerId;
	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}	
}
