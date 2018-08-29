package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.CommentDto;
import com.nts.pjt3_4.dto.FileInfoDto;
import com.nts.pjt3_4.dto.RsvUserCmtDto;

public interface RsvUserCmtService {

	public static final int LIMIT_THREE = 3;

	public static final int LIMIT_SIX = 6;

	public List<RsvUserCmtDto> getThreeComments(int productId, int start);

	public List<RsvUserCmtDto> getSixComments(int productId, int start);

	public int getCount(int productId);

	public float getAvgScore(int productId);

	public int addComment(CommentDto comment, FileInfoDto fileInfo);

	public String savedFileName(CommentDto comment);

}
