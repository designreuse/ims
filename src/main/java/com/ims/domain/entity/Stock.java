package com.ims.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "stock")
public class Stock extends AbstractEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;
	private int currentStock;
	private BigDecimal stockValue;
	private String productName;
	
	public BigDecimal getStockValue() {
		return stockValue;
	}

	public void setStockValue(BigDecimal stockValue) {
		this.stockValue = stockValue;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
