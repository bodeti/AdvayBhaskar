package com.wells.fsd.pms.entity;

import java.time.LocalDate;

public class Product {

	
	private Integer productId;
	private String productName;
	private double productCost;
	private String productDescription;
	
	public Product() {
		//left unimplemented.
	}

	public Product(Integer productId, String productName, double productCost, String productDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.productDescription = productDescription;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ ", productDescription=" + productDescription + "]";
	}

}
