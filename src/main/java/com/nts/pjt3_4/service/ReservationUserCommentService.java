package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.ReservationUserCommentDto;

public interface ReservationUserCommentService {

	public static final int LIMIT_THREE = 3;
	
	public static final int LIMIT_SIX = 6;

	public List<ReservationUserCommentDto> getThreeComments(int productId, int start);
	
	public List<ReservationUserCommentDto> getSixComments(int productId, int start);
	
	public int getCount(int productId);
	
	public float getAvgScore(int productId);

}
