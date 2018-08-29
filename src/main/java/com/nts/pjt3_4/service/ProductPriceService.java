package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ProductPrice;

public interface ProductPriceService {
	
	public List<ProductPrice> getProductPrices(int productId);
}
