package com.nts.pjt3_4.dto;

public class RsvUserCmtInfoDto {
	private RsvUserCmtDto reservationUserComment;
	private RsvInfoWithPriceDto reservationInfo;

	public RsvUserCmtDto getReservationUserComment() {
		return reservationUserComment;
	}

	public void setReservationUserComment(RsvUserCmtDto reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}

	public RsvInfoWithPriceDto getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(RsvInfoWithPriceDto reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

}
