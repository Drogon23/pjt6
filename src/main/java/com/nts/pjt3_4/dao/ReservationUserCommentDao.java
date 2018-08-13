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

import com.nts.pjt3_4.dto.ReservationUserCommentDto;
import com.nts.pjt3_4.sql.ReservationUserCommentDaoSqls;

@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentDto> rowMapper = BeanPropertyRowMapper
		.newInstance(ReservationUserCommentDto.class);

	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationUserCommentDto> selectAll(int productId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(ReservationUserCommentDaoSqls.SELECT_ALL, params, rowMapper);
	}

	public int countByProductId(int productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ReservationUserCommentDaoSqls.COUNT_BY_PRODUCT_ID, params, Integer.class);
	}

	public float avgScore(int productId) {
		Map<String, Integer> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ReservationUserCommentDaoSqls.AVG_SCORE, params, Float.class);
	}
}
