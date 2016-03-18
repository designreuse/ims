
package com.ims.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ims.domain.enums.PurchaseOrderPaymentMethod;
import com.ims.domain.enums.PurchaseOrderStatus;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String vendorName;
	private String vendorSite;
	private PurchaseOrderStatus status; 
	private PurchaseOrderPaymentMethod paymentMethod;
	private String remark;
	private Integer total;
	private Product product;
	
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
	
	@Enumerated(EnumType.STRING)
	public PurchaseOrderStatus getStatus() {
		return status;
	}
	public void setStatus(PurchaseOrderStatus status) {
		this.status = status;
	}
	
	@Enumerated(EnumType.STRING)
	public PurchaseOrderPaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PurchaseOrderPaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
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
	
	@ManyToOne
    @JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}