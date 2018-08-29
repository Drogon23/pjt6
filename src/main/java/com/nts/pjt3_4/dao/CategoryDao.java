package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.pjt3_4.dto.CategoryDto;

@Mapper
public interface CategoryDao {

	public List<CategoryDto> selectAll();

}
