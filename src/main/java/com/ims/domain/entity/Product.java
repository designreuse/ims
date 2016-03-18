package com.ims.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	private ProductDescription productDescription;
	private List<PurchaseOrder> purchaseOrder;
	private String model;
	private String sku;
	private String upc;
	private String ean;
	private String jan;
	private String isbn;
	private String mpn;
	private String location;
	private int quantity;
	private int stockStatusId;
	private String image;
	private int manufacturerId;
	private int shipping;
	private BigDecimal price;
	private int points;
	private int taxClassId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAvailable;
	private BigDecimal weight;
	private int weightClassId;
	private BigDecimal length;
	private BigDecimal width;
	private BigDecimal height;
	private int lengthClassId;
	private int subtract;
	private int minimum;
	private int sortOrder;
	private int status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAdded;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateModified;
	private int viewed;
	private int sodexo;
	private int reorderQuantity;
	private int currentStock;
	private String productName;

	@Id
	@Column(name = "product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int isStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStockStatusId() {
		return stockStatusId;
	}

	public void setStockStatusId(int stockStatusId) {
		this.stockStatusId = stockStatusId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getTaxClassId() {
		return taxClassId;
	}

	public void setTaxClassId(int taxClassId) {
		this.taxClassId = taxClassId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public int getWeightClassId() {
		return weightClassId;
	}

	public void setWeightClassId(int weightClassId) {
		this.weightClassId = weightClassId;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public int getLengthClassId() {
		return lengthClassId;
	}

	public void setLengthClassId(int lengthClassId) {
		this.lengthClassId = lengthClassId;
	}

	public int getSubtract() {
		return subtract;
	}

	public void setSubtract(int subtract) {
		this.subtract = subtract;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public int getSodexo() {
		return sodexo;
	}

	public void setSodexo(int sodexo) {
		this.sodexo = sodexo;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setPurchaseOrder(List<PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	@OneToMany
	@PrimaryKeyJoinColumn
	public List<PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}


	public int getReorderQuantity() {
		return reorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	public int getStatus() {
		return status;
	}

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
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
