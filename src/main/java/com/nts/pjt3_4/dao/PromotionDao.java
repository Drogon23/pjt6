package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.PromotionDto;
import com.nts.pjt3_4.sql.PromotionDaoSqls;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionDto> rowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<PromotionDto> selectAll() {
		return jdbc.query(PromotionDaoSqls.SELECT_ALL, Collections.emptyMap(), rowMapper);
	}

}
