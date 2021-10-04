package com.example.website.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_table")
public class Purchase{
	
	@Id
	@GeneratedValue
	@Column(name="purchase_id")
	private long purchaseId;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="product_id")
	private long productId;
	
	@GeneratedValue
	@Column(name="purchase_date")
	private LocalDateTime dateOfPurchase;
	
	@Column(name="category")
	private String categoryName;
	
	private long price;
	
	
	public long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public LocalDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDateTime now) {
		this.dateOfPurchase = now;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
}