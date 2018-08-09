package com.nts.pjt3_4.api.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.Category;
import com.nts.pjt3_4.service.CategoryService;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public Map<String, Object> listCategories() {
		List<Category> categories = categoryService.getAllCategories();

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("items", categories);
		map.put("size", categories.size());
		return map;
	}
}
