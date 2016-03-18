package com.ims.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="order_status")
public class OrderStatus {
	@Id
	@GeneratedValue
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
