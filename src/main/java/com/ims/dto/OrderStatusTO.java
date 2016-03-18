package com.ims.dto;

public class OrderStatusTO {
	Integer orderStatusId;
	Integer languageId;
	String name;
	public Integer getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
