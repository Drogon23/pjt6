package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.ProductPrice;

@Mapper
public interface ProductPriceDao {

	public List<ProductPrice> selectAllByProductId(@Param("productId") int productId);
}
