package com.wells.fsd.pms.service;

import java.util.List;

import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.exception.ProductException;

public interface ProductService {
	
	Product validateAndAdd(Product product) throws ProductException;
	Product validateAndSave(Product product) throws ProductException;
	
	boolean deleteProduct(int productId) throws ProductException;
	
	Product getProduct(int productId) throws ProductException;
	List<Product> getAllProducts() throws ProductException;

}
