package com.nts.pjt3_4.dto;

import java.util.List;

public class RsvListDto {
	private List<RsvInfoDto> notUsedRsvList;
	private List<RsvInfoDto> usedRsvList;
	private List<RsvInfoDto> canceledRsvList;

	public List<RsvInfoDto> getNotUsedRsvList() {
		return notUsedRsvList;
	}

	public void setNotUsedRsvList(List<RsvInfoDto> notUsedRsvList) {
		this.notUsedRsvList = notUsedRsvList;
	}

	public List<RsvInfoDto> getUsedRsvList() {
		return usedRsvList;
	}

	public void setUsedRsvList(List<RsvInfoDto> usedRsvList) {
		this.usedRsvList = usedRsvList;
	}

	public List<RsvInfoDto> getCanceledRsvList() {
		return canceledRsvList;
	}

	public void setCanceledRsvList(List<RsvInfoDto> canceledRsvList) {
		this.canceledRsvList = canceledRsvList;
	}

}
