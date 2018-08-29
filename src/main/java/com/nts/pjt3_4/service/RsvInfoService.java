package com.nts.pjt3_4.service;

import java.util.List;

import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;
import com.nts.pjt3_4.dto.RsvListDto;

public interface RsvInfoService {

	public RsvInfoWithPriceDto getReservationInfo(int reservationInfoId);

	public List<RsvInfoDto> getRsvInfosByEmail(String rsvEmail);

	public RsvListDto categorizeRsv(List<RsvInfoDto> rsvInfoList);

	public int addRsvInfo(RsvDto rsv);

	public float sumPrice(int rsvId);

	public int cancelRsv(int rsvId);

	public boolean isValidRsv(RsvDto rsv);
}
