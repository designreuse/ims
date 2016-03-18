package com.ims.domain.enums;

public enum ScreeningStatus {
	Qualified("Qualified"), Rejected("Rejected");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private ScreeningStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
