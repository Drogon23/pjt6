package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ReservationUserComment;

public interface ReservationUserCommentService {

	public static final int LIMIT = 3;

	public List<ReservationUserComment> getComments(int productId, int start);
	
	public int getCount(int productId);
	
	public float getAvgScore(int productId);

}
