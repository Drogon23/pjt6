package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.ReservationInfoDto;
import com.nts.pjt3_4.sql.ReservationInfoDaoSqls;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoDto.class);

	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReservationInfoDto select(int reservationInfoId) {
		Map<String, Integer> params = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(ReservationInfoDaoSqls.SELECT_ONE, params, rowMapper);
	}
}
