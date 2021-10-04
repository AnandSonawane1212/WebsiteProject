package com.example.website.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_table")
public class Product {
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private long productId;
	
	@Column(name="product_name")
	private String productName;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	@Column(name="price_of_product")
	private long priceOfProduct;
	
	@Column(name="price_type")
	private String productType;
	
	
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getPriceOfProduct() {
		return priceOfProduct;
	}
	public void setPriceOfProduct(long priceOfProduct) {
		this.priceOfProduct = priceOfProduct;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}