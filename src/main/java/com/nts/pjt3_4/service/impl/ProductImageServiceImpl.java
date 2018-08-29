package com.nts.pjt3_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ProductImageDao;
import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageDao productImageDao;

	@Override
	public ProductImageDto getProductImageByType(int productId, String type) {
		return productImageDao.selectImageByType(productId, type);
	}

}
