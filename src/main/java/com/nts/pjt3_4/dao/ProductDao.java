package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.Product;
import com.nts.pjt3_4.sql.ProductDaoSqls;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAll(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(ProductDaoSqls.SELECT_ALL, params, rowMapper);
	}

	public List<Product> selectByCategory(int start, int categoryId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		return jdbc.query(ProductDaoSqls.SELECT_BY_CATEGORY, params, rowMapper);
	}

	public Product select(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(ProductDaoSqls.SELECT_ONE, params, rowMapper);
	}

	public int countAllProduct() {
		return jdbc.queryForObject(ProductDaoSqls.COUNT_ALL_PRODUCTS, Collections.emptyMap(), Integer.class);
	}

	public int countProductByCategory(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(ProductDaoSqls.COUNT_PRODUCTS_CATEGORY, params, Integer.class);
	}
}
