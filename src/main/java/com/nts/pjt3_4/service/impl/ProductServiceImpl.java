package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ProductDao;
import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductDto> getProductsByCategory(int start, int categoryId) {
		return productDao.selectByCategory(start, categoryId, ProductService.LIMIT);
	}

	@Override
	public ProductDto getProduct(int displayInfoId) {
		return productDao.select(displayInfoId);
	}

	@Override
	public int getProductsCountByCategory(int categoryId) {
		return productDao.countProductByCategory(categoryId);
	}

}
