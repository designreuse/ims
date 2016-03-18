package com.ims.dto;

import java.math.BigDecimal;

import com.ims.domain.entity.AbstractEntity;

public class StockTO extends AbstractEntity{
	private String productId;
	private int currentStock;
	private BigDecimal stockValue;
	private String productName;
	
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

	public BigDecimal getStockValue() {
		return stockValue;
	}

	public void setStockValue(BigDecimal stockValue) {
		this.stockValue = stockValue;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
