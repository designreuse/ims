package com.ims.domain.enums;

public enum PurchaseOrderStatus {
	Cancelled("Cancelled"), Complete("Complete"), Processing("Processing"), Shipped("Shipped"), Approved("Approved"), Rejected("Rejected");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private PurchaseOrderStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
