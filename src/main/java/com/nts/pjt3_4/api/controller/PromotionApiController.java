package com.nts.pjt3_4.api.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.Promotion;
import com.nts.pjt3_4.service.PromotionService;

@RestController
@RequestMapping(path = "api/promotions")
public class PromotionApiController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public Map<String, Object> listPromotions() {
		List<Promotion> promotions = promotionService.getAllPromotionProducts();

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("items", promotions);
		map.put("size", promotions.size());
		return map;
	}
}
