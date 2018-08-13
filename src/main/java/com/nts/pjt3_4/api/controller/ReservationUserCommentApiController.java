package com.nts.pjt3_4.api.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.ReservationUserCommentDto;
import com.nts.pjt3_4.dto.ReservationUserCommentInfoDto;
import com.nts.pjt3_4.service.ReservationInfoService;
import com.nts.pjt3_4.service.ReservationUserCommentImageService;
import com.nts.pjt3_4.service.ReservationUserCommentService;

@RestController
@RequestMapping("api/reservationUserComments")
public class ReservationUserCommentApiController {

	@Autowired
	private ReservationUserCommentImageService reservationUserCommentImageService;
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	@Autowired
	private ReservationInfoService reservationInfoService;

	@GetMapping("/{productId}")
	public Map<String, Object> listComment(@PathVariable(name = "productId") int productId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		Map<String, Object> map = new LinkedHashMap<>();

		int commentsCount = reservationUserCommentService.getCount(productId);
		map.put("commentsCount", commentsCount);
		if (commentsCount > 0) {
			List<ReservationUserCommentDto> comments = reservationUserCommentService.getSixComments(productId, start);
			List<ReservationUserCommentInfoDto> commentsInfo = new ArrayList<>();
			
			float avgScore = reservationUserCommentService.getAvgScore(productId);
			avgScore = Float.parseFloat(String.format("%.2f", avgScore));
			
			for (ReservationUserCommentDto comment : comments) {
				try {
					comment.setReservationUserCommentImage(
						reservationUserCommentImageService.getCommentImage(comment.getId()));
				} catch (EmptyResultDataAccessException e) {

				}
				ReservationUserCommentInfoDto reservationUserCommentInfoDto = new ReservationUserCommentInfoDto();
				reservationUserCommentInfoDto
					.setReservationInfo(reservationInfoService.getReservationInfo(comment.getReservationInfoId()));
				reservationUserCommentInfoDto.setReservationUserComment(comment);
				commentsInfo.add(reservationUserCommentInfoDto);
			}
			map.put("commentsInfo", commentsInfo);
			map.put("avgScore", avgScore);
		} else {
			map.put("avgScore", 0);
		}
		return map;
	}
}
