package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ProductImage;

public interface ProductImageService {

	public ProductImage getProductMainImage(int productId);

	public ProductImage getProductThImage(int productId);

	public List<ProductImage> getProductEtcImage(int productId);
}
