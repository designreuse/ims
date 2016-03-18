package com.ims.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseOrderSearchTO {

	private String name;
	private String description;
	private String vendorName;
	private String vendorSite;
	private String paymentMethod;
	private Date ShippedDate;
	private String remark;
	private Integer total;
	private Date estimatedDateOfArrival;
	private Date dateReceived;
	private Integer pkey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorSite() {
		return vendorSite;
	}

	public void setVendorSite(String vendorSite) {
		this.vendorSite = vendorSite;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getShippedDate() {
		return ShippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		ShippedDate = shippedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Date getEstimatedDateOfArrival() {
		return estimatedDateOfArrival;
	}

	public void setEstimatedDateOfArrival(Date estimatedDateOfArrival) {
		this.estimatedDateOfArrival = estimatedDateOfArrival;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	
}
