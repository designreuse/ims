package com.ims.domain.enums;

public enum SmokingHabit {
	Smoker("Smoker"), Non_Smoker("Non Smoker"), Past_Smoker("Past Smoker");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private SmokingHabit(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
