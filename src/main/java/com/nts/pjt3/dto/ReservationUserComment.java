package com.nts.pjt3.dto;

import java.util.Date;

public class ReservationUserComment {
	private int id;
	private int productId;
	private int reservationInfoId;
	private double score;
	private String comment;
	private Date createDate;
	private Date modifyDate;
	private ReservationUserCommentImage reservationUserCommentImage;

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

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
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

	public ReservationUserCommentImage getReservationUserCommentImage() {
		return reservationUserCommentImage;
	}

	public void setReservationUserCommentImage(ReservationUserCommentImage reservationUserCommentImage) {
		this.reservationUserCommentImage = reservationUserCommentImage;
	}
}
