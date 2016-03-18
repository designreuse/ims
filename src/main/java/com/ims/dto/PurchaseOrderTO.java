package com.ims.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ims.domain.enums.PurchaseOrderPaymentMethod;
import com.ims.domain.enums.PurchaseOrderStatus;

public class PurchaseOrderTO extends AbstractTO {

	private static final long serialVersionUID = 1L;
	
	private List<Integer> ids;
	private List<String> names;
	private String name;
	private String description;
	private String vendorName;
	private String vendorSite;
	private PurchaseOrderStatus status; 
	private PurchaseOrderPaymentMethod paymentMethod;
	private Date ShippedDate;
	private String remark;
	private Integer total;
	private Date estimatedDateOfArrival;
	private Date dateReceived;
	private Integer id;
	private Integer quantity;
	
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
	public PurchaseOrderStatus getStatus() {
		return status;
	}
	public void setStatus(PurchaseOrderStatus status) {
		this.status = status;
	}
	public PurchaseOrderPaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PurchaseOrderPaymentMethod paymentMethod) {
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
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
