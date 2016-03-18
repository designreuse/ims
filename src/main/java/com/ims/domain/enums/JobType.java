package com.ims.domain.enums;

public enum JobType {
	
	SyncProduct("SyncProduct"), SyncOrder("SyncOrder");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private JobType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
