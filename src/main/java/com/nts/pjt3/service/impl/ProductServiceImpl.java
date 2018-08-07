package com.nts.pjt3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ProductDao;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getAllProducts(int start) {
		return productDao.selectAll(start, ProductService.LIMIT);
	}

	@Override
	public List<Product> getProductsByCategory(int start, int categoryId) {
		return productDao.selectByCategory(start, ProductService.LIMIT, categoryId);
	}

	@Override
	public Product getProduct(int displayInfoId) {
		return productDao.select(displayInfoId);
	}

	@Override
	public int getAllProductsCount() {
		return productDao.countAllProduct();
	}

	@Override
	public int getProductsCountByCategory(int categoryId) {
		return productDao.countProductByCategory(categoryId);
	}

}
