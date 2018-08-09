package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ProductImageDao;
import com.nts.pjt3_4.dto.ProductImage;
import com.nts.pjt3_4.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageDao productImageDao;

	@Override
	public ProductImage getProductMainImage(int productId) {
		return productImageDao.selectMainImageByProductId(productId);
	}

	@Override
	public ProductImage getProductThImage(int productId) {
		return productImageDao.selectThImageByProductId(productId);
	}

	@Override
	public List<ProductImage> getProductEtcImage(int productId) {
		return productImageDao.selectEtcImageByProductId(productId);
	}

}
