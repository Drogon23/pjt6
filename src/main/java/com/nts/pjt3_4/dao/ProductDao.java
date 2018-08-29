package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.ProductDto;

@Mapper
public interface ProductDao {

	public List<ProductDto> selectByCategory(@Param("start") int start, @Param("categoryId") int categoryId,
		@Param("limit") int limit);

	public ProductDto select(@Param("displayInfoId") int displayInfoId);

	public int countProductByCategory(@Param("categoryId") int categoryId);
}
