package com.wells.fsd.pms.service;

import java.util.ArrayList;
import java.util.List;

import com.wells.fsd.pms.dao.ProductDao;
import com.wells.fsd.pms.dao.ProductDaoJdbcImpl;
import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.exception.ProductException;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	
	public ProductServiceImpl() {
		productDao = new ProductDaoJdbcImpl();
	}
	
	private boolean isValidProductID(Integer productId) {
		return productId!=null && productId>0; 
	}
	
	private boolean isValidProductName(String productName) {
		return productName!=null && (productName.length()>=3 || productName.length()<=20);
	}
	
	private boolean isValidProductCost(Double productCost) {
		return productCost!=null && productCost>0; 
	}
	
	private boolean isValidProductDescription(String productDescription) {
		return productDescription!=null && (productDescription.length()>=3 || productDescription.length()<=100);
	}
	
	
	public boolean isValidProduct(Product product) throws ProductException {
		List<String> errMsgs = new ArrayList<>();
		boolean isValid=true;
		
		if(product!=null) {
			if(!isValidProductID(product.getProductId())) {
				isValid=false;
				errMsgs.add("Product id can not left blank and must be a positive number, Duplicates are not allowed");
			}
			if(!isValidProductName(product.getProductName())) {
				isValid=false;
				errMsgs.add("Product name can not left blank and must be of 3 to 20 in length");
			}
			if(!isValidProductCost(product.getProductCost())) {
				isValid=false;
				errMsgs.add("Product Costcan not be left blank and must be positive number");
			}
			if(!isValidProductDescription(product.getProductDescription())) {
				isValid=false;
				errMsgs.add("Product Description can not be left blank");
			}
			
			if(!errMsgs.isEmpty()) {
				throw new ProductException("Invalid Product: "+errMsgs);
			}
		}else {
			isValid=false;
			throw new ProductException("Product details are not supplied");
		}
		
		return isValid;
	}
	
	
	@Override
	public Product validateAndAdd(Product product) throws ProductException {
		if(isValidProduct(product)) {
			productDao.add(product);
		}
		return product;
	}

	@Override
	public Product validateAndSave(Product product) throws ProductException {
		if(isValidProduct(product)) {
			productDao.save(product);
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int productId) throws ProductException {
		return productDao.deleteById(productId);
	}

	@Override
	public Product getProduct(int productId) throws ProductException {
		return productDao.getByID(productId);
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		return productDao.getAll();
	}

}
