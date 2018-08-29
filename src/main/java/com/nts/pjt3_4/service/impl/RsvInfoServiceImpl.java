package com.nts.pjt3_4.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3_4.dao.RsvInfoDao;
import com.nts.pjt3_4.dao.RsvInfoPriceDao;
import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvInfoPriceDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;
import com.nts.pjt3_4.dto.RsvListDto;
import com.nts.pjt3_4.service.RsvInfoService;

@Service
public class RsvInfoServiceImpl implements RsvInfoService {

	@Autowired
	private RsvInfoDao rsvInfoDao;
	@Autowired
	private RsvInfoPriceDao rsvInfoPriceDao;
	private static final int CANCELED = 1;

	@Override
	public RsvInfoWithPriceDto getReservationInfo(int reservationInfoId) {
		return rsvInfoDao.select(reservationInfoId);
	}

	@Override
	public List<RsvInfoDto> getRsvInfosByEmail(String rsvEmail) {
		return rsvInfoDao.selectByEmail(rsvEmail);
	}

	@Override
	public RsvListDto categorizeRsv(List<RsvInfoDto> rsvInfoList) {
		Date rsvDate = new Date();
		Date todayDate = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		c.add(Calendar.DATE, -1);//이용 완료는 오늘 일자 제외이기 때문에 todayDate에서 하루를 빼줌
		todayDate = c.getTime();
		List<RsvInfoDto> canceledRsvList = new ArrayList<>();
		List<RsvInfoDto> usedRsvList = new ArrayList<>();
		List<RsvInfoDto> notUsedRsvList = new ArrayList<>();
		for (RsvInfoDto rsvInfo : rsvInfoList) {
			rsvInfo.setSumPrice(rsvInfoDao.sumPrice(rsvInfo.getId()));

			if (rsvInfo.getCancelFlag() == CANCELED) {
				canceledRsvList.add(rsvInfo);
			} else {
				rsvDate = rsvInfo.getReservationDate();
				if (todayDate.after(rsvDate)) {
					usedRsvList.add(rsvInfo);
				} else {
					notUsedRsvList.add(rsvInfo);
				}
			}
		}
		RsvListDto rsvList = new RsvListDto();
		rsvList.setCanceledRsvList(canceledRsvList);
		rsvList.setUsedRsvList(usedRsvList);
		rsvList.setNotUsedRsvList(notUsedRsvList);
		return rsvList;
	}

	@Override
	@Transactional
	public int addRsvInfo(RsvDto rsv) {
		rsvInfoDao.insertRsvInfo(rsv);
		List<RsvInfoPriceDto> rsvInfoPrices = rsv.getPrices();
		for (RsvInfoPriceDto rsvInfoPrice : rsvInfoPrices) {
			rsvInfoPrice.setReservationInfoId(rsv.getRsvId());
		}
		rsvInfoPriceDao.insert(rsvInfoPrices);
		return rsv.getRsvId();
	}

	@Override
	public float sumPrice(int rsvId) {
		return rsvInfoDao.sumPrice(rsvId);
	}

	@Override
	public int cancelRsv(int rsvId) {
		return rsvInfoDao.updateCancelFlag(rsvId);
	}

	@Override
	public boolean isValidRsv(RsvDto rsv) {
		String regexName = "^[가-힣]*$|^[a-zA-Z]*";
		String regexPhone = "^\\d{3}-\\d{3,4}-\\d{4}$";
		String regexEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		if (Pattern.matches(regexName, rsv.getReservationName()) && Pattern.matches(regexPhone, rsv.getReservationTel())
			&& Pattern.matches(regexEmail, rsv.getReservationEmail())) {
			return true;
		}
		return false;
	}
}
