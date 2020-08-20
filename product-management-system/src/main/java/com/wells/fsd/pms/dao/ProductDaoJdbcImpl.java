package com.wells.fsd.pms.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.exception.ProductException;
import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.dao.ConnectionFactory;
import com.wells.fsd.pms.exception.ProductException;

public class ProductDaoJdbcImpl implements ProductDao {
	
	public static final String INS_PROD_QRY = "INSERT INTO Products(pid,pname,pcost,pdescription) VALUES(?,?,?,?)";
	public static final String UPD_PROD_QRY = "UPDATE Products set pname=?,pcost=?,pdescription=? WHERE pid=?";
	public static final String DEL_PROD_QRY = "DELETE FROM Products WHERE pid=?";
	public static final String GET_PROD_BY_ID_QRY = "SELECT pid,pname,pcost,pdescription FROM Products WHERE pid=?";
	public static final String GET_ALL_PRODS_QRY = "SELECT pid,pname,pcost,pdescription FROM Products";

	@Override
	public Product add(Product product) throws ProductException {
		// TODO Auto-generated method stub
		if (product != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_PROD_QRY);) {

				pst.setInt(1, product.getProductId());
				pst.setString(2, product.getProductName());
				pst.setDouble(3,product.getProductCost()); 
				pst.setString(4, product.getProductDescription());

				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new ProductException("Saving Product failed!");
			}
		}

		
		return product;
	}

	@Override
	public Product save(Product product) throws ProductException {
		if (product != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_PROD_QRY);) {

				
				pst.setString(1, product.getProductName());
				pst.setDouble(2,product.getProductCost()); 
				pst.setString(3, product.getProductDescription());
				pst.setInt(4, product.getProductId());

				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new ProductException("Saving Product failed!");
			}
		}

		
		return product;
	}

	@Override
	public Product getByID(int productId) throws ProductException {
		Product product = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_PROD_BY_ID_QRY);) {

			pst.setInt(1, productId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new Product();
				
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductCost(rs.getDouble(3));
				product.setProductDescription(rs.getString(4));
				
				
			}

		} catch (SQLException exp) {
			throw new ProductException("Feteching Contact failed!");
		}

		return product;
	}

	@Override
	public boolean deleteById(int productId) throws ProductException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_PROD_QRY);) {

			pst.setInt(1, productId);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException exp) {
			throw new ProductException("Deleting Contact failed!");
		}
		return isDeleted;
	}

	@Override
	public List<Product> getAll() throws ProductException {
List<Product> products = new ArrayList<>();
		
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_ALL_PRODS_QRY);) {
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductCost(rs.getDouble(3));
				product.setProductDescription(rs.getString(4));
				
				products.add(product);
			}
			
			if(products.isEmpty()) {
				products=null;
			}

		} catch (SQLException exp) {
			throw new ProductException("Feteching Contact failed!");
		}
		
		return products;
	}
	
	
	
}
