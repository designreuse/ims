package com.ims.domain.enums;

public enum VolunteerStatus {
	Registered("Registered"), Rejected("Rejected"), BlackListed("BlackListed"), Approved("Approved"), Permanently_Rejected("Permanently Rejected"),Temporarily_Rejected("Temporarily Rejected");

	/*Registered("Registered"), Active("Active"), Rejected("Rejected"), BlackListed("BlackListed"), Approved("Approved"), Eligible_for_Screening(
			"Eligible for Screening"), Eligible_for_Study("Eligible for Study"), Study_Completed("Study Completed"),Permanently_Rejected("Permanently Rejected"),Temporarily_Rejected("Temporarily Rejected");
*/
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private VolunteerStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
