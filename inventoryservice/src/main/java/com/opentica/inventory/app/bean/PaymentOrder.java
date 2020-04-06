package com.opentica.inventory.app.bean;

import lombok.Data;
import lombok.ToString;

/**
 * Payment Order POJO for payment order
 *
 */
@Data
@ToString
public class PaymentOrder {
	private String productId;

	private float price;
}
