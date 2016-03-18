package com.ims.dto;

import java.math.BigDecimal;

public class StockSearchTO {
	private Integer pkey;
	private String productId;
	private Integer currentStock;
	private String stockValue;
	private String productName;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}
	
	public String getStockValue() {
		return stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
}
