package com.nts.pjt3_4.dto;

public class ReservationUserCommentInfoDto {
	private ReservationUserCommentDto reservationUserComment;
	private ReservationInfoDto reservationInfo;

	public ReservationUserCommentDto getReservationUserComment() {
		return reservationUserComment;
	}

	public void setReservationUserComment(ReservationUserCommentDto reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}

	public ReservationInfoDto getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(ReservationInfoDto reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

}
