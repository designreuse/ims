package com.ims.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderTO {
		private Integer orderId;
		//private Integer invoiceNumber;
		private String invoicePrefix;
		private Integer storeId;
		private String storeName;
		//private String stoteUrl;
		private Integer customerId;
		private Integer customerGroupId;
		private String firstName;
		//private String lastName;
		private String email;
		private String telephone;
		//private String fax;
		private String paymentFirstName;
		//private String paymentLastName;
		//private String paymentCompany;
		//private String paymentCompanyId;
		//private String paymentTaxId;
		//private String paymentAddressOne;
		//private String paymentAddressTwo;
		//private String paymentCity;
		//private String paymentPostCode;
		//private String paymentCountry;
		//private Integer paymentCountryId;
		//private String paymentZone;
		//private Integer paymentZoneId;
		//private String paymentAddressFormat;
		private String paymentMethod;
		//private String paymentCode;
		private String shippingFirstName;
		//private String shippingLastName;
		private String shippingCompany;
		//private String shippingAddressOne;
		//private String shippingAddressTwo;
		//private String shippingCity;
		//private String shippingPostCode;
		private String shippingCountry;
		private Integer shippingCountryId;
		private String shippingZone;
		private Integer shippingZoneId;
		private String shippingAddressFormat;
		private String shippingMethod;
		//private String shippingCode;
		//private String comment;
		//private BigDecimal total;
		private Integer orderStatusId;
		//private Integer affiliateId;
		//private BigDecimal commission;
		//private Integer languageId;
		private Integer currencyId;
		private String currencyCode;
		//private BigDecimal currencyValue;
		private String ip;
		//private String forwardIp;
		private String userAgent;
		private String acceptLanguage;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date dateAdded;
		/*@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date dateModified;*/
		//private BigDecimal sodexo;
		private String flag;
		
	/*	public Integer getInvoiceNumber() {
			return invoiceNumber;
		}
		public void setInvoiceNumber(Integer invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}*/
		public String getInvoicePrefix() {
			return invoicePrefix;
		}
		public void setInvoicePrefix(String invoicePrefix) {
			this.invoicePrefix = invoicePrefix;
		}
		public Integer getStoreId() {
			return storeId;
		}
		public void setStoreId(Integer storeId) {
			this.storeId = storeId;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
/*		public String getStoteUrl() {
			return stoteUrl;
		}
		public void setStoteUrl(String stoteUrl) {
			this.stoteUrl = stoteUrl;
		}*/
		public Integer getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}
		public Integer getCustomerGroupId() {
			return customerGroupId;
		}
		public void setCustomerGroupId(Integer customerGroupId) {
			this.customerGroupId = customerGroupId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
/*		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}*/
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
	/*	public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}*/
		public String getPaymentFirstName() {
			return paymentFirstName;
		}
		public void setPaymentFirstName(String paymentFirstName) {
			this.paymentFirstName = paymentFirstName;
		}
	/*	public String getPaymentLastName() {
			return paymentLastName;
		}
		public void setPaymentLastName(String paymentLastName) {
			this.paymentLastName = paymentLastName;
		}*/
	/*	public String getPaymentCompany() {
			return paymentCompany;
		}
		public void setPaymentCompany(String paymentCompany) {
			this.paymentCompany = paymentCompany;
		}
		public String getPaymentCompanyId() {
			return paymentCompanyId;
		}
		public void setPaymentCompanyId(String paymentCompanyId) {
			this.paymentCompanyId = paymentCompanyId;
		}
		public String getPaymentTaxId() {
			return paymentTaxId;
		}
		public void setPaymentTaxId(String paymentTaxId) {
			this.paymentTaxId = paymentTaxId;
		}*/
	/*	public String getPaymentAddressOne() {
			return paymentAddressOne;
		}
		public void setPaymentAddressOne(String paymentAddressOne) {
			this.paymentAddressOne = paymentAddressOne;
		}
		public String getPaymentAddressTwo() {
			return paymentAddressTwo;
		}
		public void setPaymentAddressTwo(String paymentAddressTwo) {
			this.paymentAddressTwo = paymentAddressTwo;
		}
		public String getPaymentCity() {
			return paymentCity;
		}
		public void setPaymentCity(String paymentCity) {
			this.paymentCity = paymentCity;
		}*/
	/*	public String getPaymentPostCode() {
			return paymentPostCode;
		}
		public void setPaymentPostCode(String paymentPostCode) {
			this.paymentPostCode = paymentPostCode;
		}*/
		/*public String getPaymentCountry() {
			return paymentCountry;
		}
		public void setPaymentCountry(String paymentCountry) {
			this.paymentCountry = paymentCountry;
		}*/
/*		public Integer getPaymentCountryId() {
			return paymentCountryId;
		}
		public void setPaymentCountryId(Integer paymentCountryId) {
			this.paymentCountryId = paymentCountryId;
		}*/
		/*public String getPaymentZone() {
			return paymentZone;
		}
		public void setPaymentZone(String paymentZone) {
			this.paymentZone = paymentZone;
		}*/
		/*public Integer getPaymentZoneId() {
			return paymentZoneId;
		}
		public void setPaymentZoneId(Integer paymentZoneId) {
			this.paymentZoneId = paymentZoneId;
		}*/
	/*	public String getPaymentAddressFormat() {
			return paymentAddressFormat;
		}
		public void setPaymentAddressFormat(String paymentAddressFormat) {
			this.paymentAddressFormat = paymentAddressFormat;
		}*/
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
	/*	public String getPaymentCode() {
			return paymentCode;
		}
		public void setPaymentCode(String paymentCode) {
			this.paymentCode = paymentCode;
		}*/
		public String getShippingFirstName() {
			return shippingFirstName;
		}
		public void setShippingFirstName(String shippingFirstName) {
			this.shippingFirstName = shippingFirstName;
		}
	/*	public String getShippingLastName() {
			return shippingLastName;
		}
		public void setShippingLastName(String shippingLastName) {
			this.shippingLastName = shippingLastName;
		}*/
		public String getShippingCompany() {
			return shippingCompany;
		}
		public void setShippingCompany(String shippingCompany) {
			this.shippingCompany = shippingCompany;
		}
		/*public String getShippingAddressOne() {
			return shippingAddressOne;
		}
		public void setShippingAddressOne(String shippingAddressOne) {
			this.shippingAddressOne = shippingAddressOne;
		}*/
	/*	public String getShippingAddressTwo() {
			return shippingAddressTwo;
		}
		public void setShippingAddressTwo(String shippingAddressTwo) {
			this.shippingAddressTwo = shippingAddressTwo;
		}
		public String getShippingCity() {
			return shippingCity;
		}
		public void setShippingCity(String shippingCity) {
			this.shippingCity = shippingCity;
		}*/
	/*	public String getShippingPostCode() {
			return shippingPostCode;
		}
		public void setShippingPostCode(String shippingPostCode) {
			this.shippingPostCode = shippingPostCode;
		}*/
		public String getShippingCountry() {
			return shippingCountry;
		}
		public void setShippingCountry(String shippingCountry) {
			this.shippingCountry = shippingCountry;
		}
		public Integer getShippingCountryId() {
			return shippingCountryId;
		}
		public void setShippingCountryId(Integer shippingCountryId) {
			this.shippingCountryId = shippingCountryId;
		}
		public String getShippingZone() {
			return shippingZone;
		}
		public void setShippingZone(String shippingZone) {
			this.shippingZone = shippingZone;
		}
		public Integer getShippingZoneId() {
			return shippingZoneId;
		}
		public void setShippingZoneId(Integer shippingZoneId) {
			this.shippingZoneId = shippingZoneId;
		}
		public String getShippingAddressFormat() {
			return shippingAddressFormat;
		}
		public void setShippingAddressFormat(String shippingAddressFormat) {
			this.shippingAddressFormat = shippingAddressFormat;
		}
		public String getShippingMethod() {
			return shippingMethod;
		}
		public void setShippingMethod(String shippingMethod) {
			this.shippingMethod = shippingMethod;
		}
/*		public String getShippingCode() {
			return shippingCode;
		}
		public void setShippingCode(String shippingCode) {
			this.shippingCode = shippingCode;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}*/
	/*	public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(BigDecimal total) {
			this.total = total;
		}*/
		public Integer getOrderStatusId() {
			return orderStatusId;
		}
		public void setOrderStatusId(Integer orderStatusId) {
			this.orderStatusId = orderStatusId;
		}
	/*	public Integer getAffiliateId() {
			return affiliateId;
		}
		public void setAffiliateId(Integer affiliateId) {
			this.affiliateId = affiliateId;
		}
		public BigDecimal getCommission() {
			return commission;
		}
		public void setCommission(BigDecimal commission) {
			this.commission = commission;
		}
		public Integer getLanguageId() {
			return languageId;
		}
		public void setLanguageId(Integer languageId) {
			this.languageId = languageId;
		}*/
		public Integer getCurrencyId() {
			return currencyId;
		}
		public void setCurrencyId(Integer currencyId) {
			this.currencyId = currencyId;
		}
		public String getCurrencyCode() {
			return currencyCode;
		}
		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}
	/*	public BigDecimal getCurrencyValue() {
			return currencyValue;
		}
		public void setCurrencyValue(BigDecimal currencyValue) {
			this.currencyValue = currencyValue;
		}*/
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		/*public String getForwardIp() {
			return forwardIp;
		}
		public void setForwardIp(String forwardIp) {
			this.forwardIp = forwardIp;
		}*/
		public String getUserAgent() {
			return userAgent;
		}
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
		}
		public String getAcceptLanguage() {
			return acceptLanguage;
		}
		public void setAcceptLanguage(String acceptLanguage) {
			this.acceptLanguage = acceptLanguage;
		}
		/*public Date getDateAdded() {
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
*/	/*	public BigDecimal getSodexo() {
			return sodexo;
		}
		public void setSodexo(BigDecimal sodexo) {
			this.sodexo = sodexo;
		}*/
		public Integer getOrderId() {
			return orderId;
		}
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public Date getDateAdded() {
			return dateAdded;
		}
		public void setDateAdded(Date dateAdded) {
			this.dateAdded = dateAdded;
		}
}
