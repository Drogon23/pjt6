package com.nts.pjt3_4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.pjt3_4.dao.ReservationInfoDao;
import com.nts.pjt3_4.dto.ReservationInfoDto;
import com.nts.pjt3_4.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

	@Autowired
	private ReservationInfoDao reservationInfoDao;

	@Override
	public ReservationInfoDto getReservationInfo(int reservationInfoId) {
		return reservationInfoDao.select(reservationInfoId);
	}
}
