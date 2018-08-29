package com.nts.pjt3_4.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3_4.dao.RsvInfoDao;
import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;

@RunWith(MockitoJUnitRunner.class)
public class RsvInfoServiceImplTest {

	@Mock
	private RsvInfoDao rsvInfoDao;
	@Mock
	private RsvInfoWithPriceDto rsvInfo;
	@Mock
	private RsvDto rsv;
	@Mock
	private List<RsvInfoDto> rsvInfoList;
	@InjectMocks
	private RsvInfoServiceImpl reservationInfoService;

	@Test
	public void testGetReservationInfo() {
		given(rsvInfoDao.select(1)).willReturn(rsvInfo);
		assertThat(rsvInfo, is(reservationInfoService.getReservationInfo(1)));
	}

	@Test
	public void testGetRsvInfosByEmail() {
		given(rsvInfoDao.selectByEmail("email")).willReturn(rsvInfoList);
		assertThat(rsvInfoList, is(reservationInfoService.getRsvInfosByEmail("email")));
	}

	

}
