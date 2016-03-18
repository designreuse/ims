package com.ims.domain.enums;

public enum FoodHabit {
	Veg("Veg"), Non_Veg("Non Veg");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private FoodHabit(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
