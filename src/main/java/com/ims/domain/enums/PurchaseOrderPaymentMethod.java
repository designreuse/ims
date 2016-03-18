package com.ims.domain.enums;

public enum PurchaseOrderPaymentMethod {
	CashOndelivery("CashOndelivery"), Mobomoney("Mobomoney"), PayUMoney("PayUMoney"),Other("other");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private PurchaseOrderPaymentMethod(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
