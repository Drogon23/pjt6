package com.nts.pjt3_4.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.CommentDto;
import com.nts.pjt3_4.dto.FileInfoDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;
import com.nts.pjt3_4.dto.RsvUserCmtApiDto;
import com.nts.pjt3_4.dto.RsvUserCmtDto;
import com.nts.pjt3_4.dto.RsvUserCmtInfoDto;
import com.nts.pjt3_4.service.RsvInfoService;
import com.nts.pjt3_4.service.RsvUserCmtImgService;
import com.nts.pjt3_4.service.RsvUserCmtService;

@RestController
@RequestMapping("/api/reservationUserComments")
public class RsvUserCmtApiController {

	@Autowired
	private RsvUserCmtImgService rsvUserCmtImgService;
	@Autowired
	private RsvUserCmtService rsvUserCmtService;
	@Autowired
	private RsvInfoService rsvInfoService;

	@GetMapping("/{productId}")
	public RsvUserCmtApiDto listComment(@PathVariable(name = "productId") int productId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		int commentsCount = rsvUserCmtService.getCount(productId);
		RsvUserCmtApiDto rsvUserCmtApiDto = new RsvUserCmtApiDto();
		rsvUserCmtApiDto.setCommentsCount(commentsCount);
		if (commentsCount > 0) {
			List<RsvUserCmtDto> comments = rsvUserCmtService.getSixComments(productId, start);
			List<RsvUserCmtInfoDto> commentsInfo = new ArrayList<>();

			float avgScore = rsvUserCmtService.getAvgScore(productId);
			avgScore = Float.parseFloat(String.format("%.1f", avgScore));

			for (RsvUserCmtDto comment : comments) {
				comment.setRsvUserCmtImg(
					rsvUserCmtImgService.getCommentImage(comment.getId()));

				RsvUserCmtInfoDto rsvUserCmtInfo = new RsvUserCmtInfoDto();
				RsvInfoWithPriceDto rsvInfoWithPrice = rsvInfoService.getReservationInfo(comment.getRsvId());
				String email = rsvInfoWithPrice.getReservationEmail();
				email = email.substring(0, 4).concat("****");
				rsvInfoWithPrice.setReservationEmail(email);
				rsvUserCmtInfo.setReservationInfo(rsvInfoWithPrice);
				rsvUserCmtInfo.setReservationUserComment(comment);
				commentsInfo.add(rsvUserCmtInfo);
			}
			rsvUserCmtApiDto.setCommentsInfo(commentsInfo);
			rsvUserCmtApiDto.setAvgScore(avgScore);
		} else {
			rsvUserCmtApiDto.setAvgScore(0);
		}
		return rsvUserCmtApiDto;
	}

	@PostMapping("/{productId}")
	public String addComment(@PathVariable(name = "productId") int productId, CommentDto comment) {
		comment.setProductId(productId);
		String saveFileName = rsvUserCmtService.savedFileName(comment);
		FileInfoDto fileInfo = null;
		if ("".equals(saveFileName) == false) {
			saveFileName = "commentImg" + File.separator + productId + File.separator + saveFileName;
			fileInfo = new FileInfoDto(comment.getImg(), saveFileName);
		}
		int isSuccess = rsvUserCmtService.addComment(comment, fileInfo);
		if (isSuccess == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

}
