package com.nts.pjt3_4.dto;

import org.springframework.web.multipart.MultipartFile;

public class CommentDto {
	private int id;
	private MultipartFile img;
	private int rsvId;
	private int productId;
	private int score;
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}

	public int getRsvId() {
		return rsvId;
	}

	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
