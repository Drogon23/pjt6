package com.nts.pjt3_4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ReservationUserCommentDao;
import com.nts.pjt3_4.dto.ReservationUserComment;
import com.nts.pjt3_4.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {

	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;

	@Override
	public List<ReservationUserComment> getComments(int productId, int start) {
		return reservationUserCommentDao.selectAll(productId, start, ReservationUserCommentService.LIMIT);
	}

	@Override
	public int getCount(int productId) {
		return reservationUserCommentDao.countByProductId(productId);
	}

	@Override
	public float getAvgScore(int productId) {
		return reservationUserCommentDao.avgScore(productId);
	}

}
