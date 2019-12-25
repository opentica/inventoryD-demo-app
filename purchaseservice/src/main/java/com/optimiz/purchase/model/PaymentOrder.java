package com.optimiz.purchase.model;

/**
 * Payment Order POJO for payment order
 *
 */
public class PaymentOrder {
	private String productId;

	private float price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public float getPayment() {
		return price;
	}

	public void setPayment(float payment) {
		this.price = payment;
	}
}
