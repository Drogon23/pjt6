package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3_4.dao.RsvInfoPriceDao;
import com.nts.pjt3_4.dto.RsvInfoPriceDto;
import com.nts.pjt3_4.service.RsvInfoPriceService;

@Service
public class RsvInfoPriceServiceImpl implements RsvInfoPriceService {

	@Autowired
	private RsvInfoPriceDao rsvInfoPriceDao;

	@Override
	public List<RsvInfoPriceDto> getRsvInfoPrices(int rsvInfoId) {
		return rsvInfoPriceDao.select(rsvInfoId);
	}

}
