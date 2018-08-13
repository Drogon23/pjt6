package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.ProductPriceDto;
import com.nts.pjt3_4.sql.ProductPriceDaoSqls;

@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPriceDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDto.class);

	public ProductPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductPriceDto> selectAllByProductId(int productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);
		return jdbc.query(ProductPriceDaoSqls.SELECT_ALL_BY_PRODUCT_ID, params, rowMapper);
	}
}
