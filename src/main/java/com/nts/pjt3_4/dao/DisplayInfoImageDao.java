package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.DisplayInfoImageDto;
import com.nts.pjt3_4.sql.DisplayInfoImageDaoSqls;

@Repository
public class DisplayInfoImageDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImageDto> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);

	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoImageDto selectBydisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(DisplayInfoImageDaoSqls.SELECT_BY_DISPLAYINFO_ID, params, rowMapper);
	}
}
