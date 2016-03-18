package com.ims.domain.enums;

public enum ImageType {
	Biometric("Biometric"), Camera("Camera");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private ImageType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
