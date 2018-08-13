package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.sql.ProductImageDaoSqls;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImageDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);

	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ProductImageDto selectThImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ProductImageDaoSqls.SELECT_TH_IMG_BY_PRODUCT_ID, params, rowMapper);
	}

	public ProductImageDto selectMainImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ProductImageDaoSqls.SELECT_MAIN_IMG_BY_PRODUCT_ID, params, rowMapper);
	}

	public List<ProductImageDto> selectEtcImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.query(ProductImageDaoSqls.SELECT_ETC_IMG_BY_PRODUCT_ID, params, rowMapper);
	}
}
