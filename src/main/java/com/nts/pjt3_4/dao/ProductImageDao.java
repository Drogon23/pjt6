package com.nts.pjt3_4.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.ProductImageDto;

@Mapper
public interface ProductImageDao {

	public ProductImageDto selectImageByType(@Param("productId") int productId, @Param("type") String type);

}
