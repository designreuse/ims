package com.ims.domain.enums;

public enum Gender {
	Male("Male"), Female("Female");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private Gender(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
