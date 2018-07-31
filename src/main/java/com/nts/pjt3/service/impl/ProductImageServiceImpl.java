package com.nts.pjt3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3.dao.ProductImageDao;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageDao productImageDao;

	@Override
	public ProductImage getProductImage(int productId) {
		return productImageDao.selectByProductId(productId);
	}

}
