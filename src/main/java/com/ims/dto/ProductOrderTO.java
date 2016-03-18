package com.ims.dto;
import java.math.BigDecimal;


public class ProductOrderTO {
	Integer orderProductId;
	Integer orderId;
	Integer productId;
	String name;
	String model;
	BigDecimal price;
	BigDecimal total;
	BigDecimal tax;
	Integer reward;
	Integer quantity;
	
	public Integer getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(Integer orderProductId) {
		this.orderProductId = orderProductId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
