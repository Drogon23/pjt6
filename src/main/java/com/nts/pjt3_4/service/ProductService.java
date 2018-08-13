package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ProductDto;

public interface ProductService {
	
	public static final int LIMIT = 4;
	
	public List<ProductDto> getAllProducts(int start);
	
	public List<ProductDto> getProductsByCategory(int start, int categoryId);
	
	public ProductDto getProduct(int displayInfoId);
	
	public int getAllProductsCount();
	
	public int getProductsCountByCategory(int categoryId);	

}
