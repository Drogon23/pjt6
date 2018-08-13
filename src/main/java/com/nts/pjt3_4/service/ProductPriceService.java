package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ProductPriceDto;

public interface ProductPriceService {
	
	public List<ProductPriceDto> getProductPrices(int productId);
}
