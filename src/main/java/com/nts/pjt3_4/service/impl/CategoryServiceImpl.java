package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.CategoryDao;
import com.nts.pjt3_4.dto.CategoryDto;
import com.nts.pjt3_4.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryDao.selectAll();
	}

}
