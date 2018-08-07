package com.nts.pjt3.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.sql.ProductImageDaoSqls;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ProductImage selectThImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ProductImageDaoSqls.SELECT_TH_IMG_BY_PRODUCT_ID, params, rowMapper);
	}

	public ProductImage selectMainImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ProductImageDaoSqls.SELECT_MAIN_IMG_BY_PRODUCT_ID, params, rowMapper);
	}

	public List<ProductImage> selectEtcImageByProductId(int productId) {
		Map<String, Object> params = Collections.singletonMap("productId", productId);
		return jdbc.query(ProductImageDaoSqls.SELECT_ETC_IMG_BY_PRODUCT_ID, params, rowMapper);
	}
}
