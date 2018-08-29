package com.nts.pjt3_4.dto;

import java.util.List;

public class RsvUserCmtApiDto {
	private int commentsCount;
	private List<RsvUserCmtInfoDto> commentsInfo;
	private float avgScore;

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public List<RsvUserCmtInfoDto> getCommentsInfo() {
		return commentsInfo;
	}

	public void setCommentsInfo(List<RsvUserCmtInfoDto> commentsInfo) {
		this.commentsInfo = commentsInfo;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}

}
