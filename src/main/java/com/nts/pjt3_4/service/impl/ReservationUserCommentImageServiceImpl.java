package com.nts.pjt3_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ReservationUserCommentImageDao;
import com.nts.pjt3_4.dto.ReservationUserCommentImage;
import com.nts.pjt3_4.service.ReservationUserCommentImageService;

@Service
public class ReservationUserCommentImageServiceImpl implements ReservationUserCommentImageService {

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	@Override
	public ReservationUserCommentImage getCommentImage(int commentId) {
		return reservationUserCommentImageDao.selectByCommentId(commentId);
	}

}
