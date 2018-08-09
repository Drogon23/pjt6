package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.ReservationUserCommentImage;
import com.nts.pjt3_4.sql.ReservationUserCommentImageDaoSqls;

@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper
		.newInstance(ReservationUserCommentImage.class);

	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReservationUserCommentImage selectByCommentId(int commentId) {
		Map<String, Object> params = Collections.singletonMap("commentId", commentId);
		return jdbc.queryForObject(ReservationUserCommentImageDaoSqls.SELECT_BY_COMMENT_ID, params, rowMapper);
	}
}
