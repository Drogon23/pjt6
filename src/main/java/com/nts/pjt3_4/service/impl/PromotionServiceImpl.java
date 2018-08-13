package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.PromotionDao;
import com.nts.pjt3_4.dto.PromotionDto;
import com.nts.pjt3_4.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;

	@Override
	public List<PromotionDto> getAllPromotionProducts() {
		return promotionDao.selectAll();
	}

}
