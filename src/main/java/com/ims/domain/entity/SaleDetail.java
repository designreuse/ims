package com.ims.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="saleDetails")
public class SaleDetail {
	@Id
	@GeneratedValue
	private int id;
	private int salesId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
}
