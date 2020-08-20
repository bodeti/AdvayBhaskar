package com.wells.fsd.pms.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.wells.fsd.pms.entity.Product;
import com.wells.fsd.pms.exception.ProductException;
import com.wells.fsd.pms.service.ProductService;
import com.wells.fsd.pms.service.ProductServiceImpl;

public class PMSApplication {
	
	
	public static final ProductService productService = new ProductServiceImpl();
	public static final Scanner scanner = new Scanner(System.in);
	
	private static void doAdd() {
Product product = new Product();
		
		System.out.print("Product Id> ");
		product.setProductId(scanner.nextInt());
		
		System.out.print("Product Name> ");
		product.setProductName(scanner.next());
		
		System.out.print("Product Cost> ");
		product.setProductCost(scanner.nextDouble());
		
		System.out.print("Product Description> ");
		product.setProductDescription(scanner.next());
		
		
				
		try {
			productService.validateAndAdd(product);
			System.out.println("Product Got Added!");
		} catch (ProductException exp) {
			System.out.println(exp.getMessage());
		}
		
		
		
	}
	
	private static void doDelete() {
		try {
			System.out.println("Product Id: ");
			int productId = scanner.nextInt();

			boolean isDeleted = productService.deleteProduct(productId);

			if (!isDeleted) {
				System.out.println("No Such Record Found.");
			} else {
				System.out.println("Product Deleted!");
			}
		} catch (ProductException exp) {
			System.out.println(exp.getMessage());
		}
		
		
	}
	
	private static void doFind() {
		
		try {
			System.out.println("Product Id: ");
			int productId = scanner.nextInt();

			Product product = productService.getProduct(productId);

			if (product == null) {
				System.out.println("No Such Record Found.");
			} else {
				System.out.println(product);
			}
		} catch (ProductException exp) {
			System.out.println(exp.getMessage());
		}
	}
	private static void doList() {
		
		try {
			List<Product> products = productService.getAllProducts();

			if (products == null) {
				System.out.println("No Records To Display.");
			} else {
				for (Product c : products) {
					System.out.println(c);
				}
			}
		} catch (ProductException exp) {
			System.out.println(exp.getMessage());
		}
		
		
	}
	
	

	public static void main(String[] args) {
		
		Menu[] menus = Menu.values();
		Menu selectedMenu = null;

		while (selectedMenu != Menu.QUIT) {
			System.out.println("Choice\tOperation");
			for (Menu m : menus) {
				System.out.println(m.ordinal() + "\t" + m);
			}

			System.out.print("Choice> ");
			int ch = scanner.nextInt();

			if (ch >= 0 && ch < menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case ADD:
					doAdd();
					break;
				case LIST:
					doList();
					break;
				case FIND:
					doFind();
					break;
				case DELETE:
					doDelete();
					break;
				}
			} else {
				selectedMenu = null;
				System.out.println("Unkown Choice");
			}
		}

		scanner.close();
		System.out.println("APPLICATION TERMINATED!");
	}

		

	
		
	

}
