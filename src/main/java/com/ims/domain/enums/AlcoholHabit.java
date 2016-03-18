package com.ims.domain.enums;

public enum AlcoholHabit {
	Drinker("Drinker"), Non_Drinker("Non Drinker"), Past_Drinker("Past Drinker");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private AlcoholHabit(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
