package com.nts.pjt3_4.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.pjt3_4.dto.CategoryDto;
import com.nts.pjt3_4.sql.CategoryDaoSqls;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CategoryDto> selectAll() {
		return jdbc.query(CategoryDaoSqls.SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
}
