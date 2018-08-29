package com.nts.pjt3_4.service;

import com.nts.pjt3_4.dto.ProductImageDto;

public interface ProductImageService {

	public ProductImageDto getProductImageByType(int productId, String type);

}
