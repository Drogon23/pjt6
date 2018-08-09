package com.nts.pjt3_4.dto;

public class ReservationUserCommentInfoDto {
	private ReservationUserComment reservationUserComment;
	private ReservationInfo reservationInfo;

	public ReservationUserComment getReservationUserComment() {
		return reservationUserComment;
	}

	public void setReservationUserComment(ReservationUserComment reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}

	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

}
