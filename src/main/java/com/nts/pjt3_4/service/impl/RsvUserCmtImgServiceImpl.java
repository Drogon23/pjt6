package com.nts.pjt3_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.RsvUserCmtImgDao;
import com.nts.pjt3_4.dto.RsvUserCmtImgDto;
import com.nts.pjt3_4.service.RsvUserCmtImgService;

@Service
public class RsvUserCmtImgServiceImpl implements RsvUserCmtImgService {

	@Autowired
	private RsvUserCmtImgDao rsvUserCmtImgDao;

	@Override
	public RsvUserCmtImgDto getCommentImage(int commentId) {
		return rsvUserCmtImgDao.selectByCommentId(commentId);
	}

}
