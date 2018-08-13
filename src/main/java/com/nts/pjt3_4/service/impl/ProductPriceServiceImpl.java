package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ProductPriceDao;
import com.nts.pjt3_4.dto.ProductPriceDto;
import com.nts.pjt3_4.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	private ProductPriceDao productPriceDao;

	@Override
	public List<ProductPriceDto> getProductPrices(int productId) {
		return productPriceDao.selectAllByProductId(productId);
	}

}
