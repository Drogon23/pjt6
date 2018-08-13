package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ProductImageDto;

public interface ProductImageService {

	public ProductImageDto getProductMainImage(int productId);

	public ProductImageDto getProductThImage(int productId);

	public List<ProductImageDto> getProductEtcImage(int productId);
}
