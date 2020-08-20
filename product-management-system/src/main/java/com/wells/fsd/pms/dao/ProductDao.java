package com.wells.fsd.pms.dao;

import java.util.List;

import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.exception.ProductException;

public interface ProductDao {

	Product add(Product product) throws ProductException;
	Product save(Product product) throws ProductException;
	Product getByID(int productId) throws ProductException;
	boolean deleteById(int productId) throws ProductException;
	List<Product> getAll() throws ProductException;
	
}
