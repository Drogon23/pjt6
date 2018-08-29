package com.nts.pjt3_4.dto;

import java.util.Date;

public class RsvUserCmtDto {
	private int id;
	private int productId;
	private int rsvId;
	private float score;
	private String comment;
	private Date createDate;
	private Date modifyDate;
	private RsvUserCmtImgDto rsvUserCmtImg;

	public RsvUserCmtDto() {

	}

	public RsvUserCmtDto(int productId, int rsvId, float score, String comment) {
		this.productId = productId;
		this.rsvId = rsvId;
		this.score = score;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getRsvId() {
		return rsvId;
	}

	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public RsvUserCmtImgDto getRsvUserCmtImg() {
		return rsvUserCmtImg;
	}

	public void setRsvUserCmtImg(RsvUserCmtImgDto rsvUserCmtImg) {
		this.rsvUserCmtImg = rsvUserCmtImg;
	}

}
