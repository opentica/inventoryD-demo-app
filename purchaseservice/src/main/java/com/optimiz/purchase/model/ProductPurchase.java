package com.optimiz.purchase.model;

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
	private String productName;
	private float price;
}
