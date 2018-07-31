package com.nts.pjt3.service;

import java.util.List;

import com.nts.pjt3.dto.Product;

public interface ProductService {
	
	public static final Integer LIMIT = 4;
	
	public List<Product> getAllProducts(int start);
	
	public List<Product> getProductsByCategory(int start, int categoryId);
	
	public Product getProduct(int displayInfoId);
	
	public int getAllProductsCount();
	
	public int getProductsCountByCategory(int categoryId);	

}
