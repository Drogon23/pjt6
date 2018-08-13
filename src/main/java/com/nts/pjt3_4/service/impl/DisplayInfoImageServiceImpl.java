package com.nts.pjt3_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.DisplayInfoImageDao;
import com.nts.pjt3_4.dto.DisplayInfoImageDto;
import com.nts.pjt3_4.service.DisplayInfoImageService;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService{
	
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;
	
	@Override
	public DisplayInfoImageDto getFileInfo(int displayInfoId) {
		return displayInfoImageDao.selectBydisplayInfoId(displayInfoId);
	}

}
