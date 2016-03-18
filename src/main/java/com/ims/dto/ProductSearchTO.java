package com.ims.dto;

import java.sql.Timestamp;
import java.util.Date;


public class ProductSearchTO extends AbstractTO {

	private static final long serialVersionUID = -7579422649447247274L;

	private Integer productId;
	private String model;
	private String sku;
	private String upc;
	private String ean;
	private String jan;
	private String isbn;
	private String mpn;
	private String location;
	private Integer quantity;
	private int currentStock;
	private Integer stockStatusId;
	private String image;
	private Integer manufacturerId;
	private Integer shipping;
	private String price;
	private Integer poIntegers;
	private Integer taxClassId;
	private Date dateAvailable;
	private String weight;
	private Integer weightClassId;
	private String length;
	private String width;
	private String height;
	private Integer lengthClassId;
	private Integer subtract;
	private Integer minimum;
	private Integer sortOrder;
	private Integer status;
	private Timestamp dateAdded;
	private Timestamp dateModified;
	private Integer viewed;
	private Integer sodexo;
	private String productName;
	private Integer reorderQuantity;
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getModel() {
		return model;
	}

	public Integer getStatus() {
		return status;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer isStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStockStatusId() {
		return stockStatusId;
	}

	public void setStockStatusId(Integer stockStatusId) {
		this.stockStatusId = stockStatusId;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getPoIntegers() {
		return poIntegers;
	}

	public void setPoIntegers(Integer poIntegers) {
		this.poIntegers = poIntegers;
	}

	public Integer getTaxClassId() {
		return taxClassId;
	}

	public void setTaxClassId(Integer taxClassId) {
		this.taxClassId = taxClassId;
	}

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getWeightClassId() {
		return weightClassId;
	}

	public void setWeightClassId(Integer weightClassId) {
		this.weightClassId = weightClassId;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getLengthClassId() {
		return lengthClassId;
	}

	public void setLengthClassId(Integer lengthClassId) {
		this.lengthClassId = lengthClassId;
	}

	public Integer getSubtract() {
		return subtract;
	}

	public void setSubtract(Integer subtract) {
		this.subtract = subtract;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Timestamp getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

	public Integer getViewed() {
		return viewed;
	}

	public void setViewed(Integer viewed) {
		this.viewed = viewed;
	}

	public Integer getSodexo() {
		return sodexo;
	}

	public void setSodexo(Integer sodexo) {
		this.sodexo = sodexo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getReorderQuantity() {
		return reorderQuantity;
	}

	public void setReorderQuantity(Integer reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	public Integer getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}

}
