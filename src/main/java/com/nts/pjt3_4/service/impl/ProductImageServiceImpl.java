package com.nts.pjt3_4.service.impl;

import java.util.List;

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
	public ProductImageDto getProductMainImage(int productId) {
		return productImageDao.selectMainImageByProductId(productId);
	}

	@Override
	public ProductImageDto getProductThImage(int productId) {
		return productImageDao.selectThImageByProductId(productId);
	}

	@Override
	public List<ProductImageDto> getProductEtcImage(int productId) {
		return productImageDao.selectEtcImageByProductId(productId);
	}

}
