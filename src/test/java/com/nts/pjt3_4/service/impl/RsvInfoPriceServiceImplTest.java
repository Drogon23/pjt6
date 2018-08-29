package com.nts.pjt3_4.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3_4.config.ApplicationConfig;
import com.nts.pjt3_4.dao.RsvInfoPriceDao;
import com.nts.pjt3_4.dto.RsvInfoPriceDto;

@Transactional
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class RsvInfoPriceServiceImplTest {

	@Mock
	private RsvInfoPriceDao rsvInfoPriceDao;
	@Mock
	private List<RsvInfoPriceDto> rsvInfoPriceList;
	@InjectMocks
	private RsvInfoPriceServiceImpl rsvInfoPriceService;

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testAddRsvInfoPrices() {
		given(rsvInfoPriceDao.select(1)).willReturn(rsvInfoPriceList);
		assertThat(rsvInfoPriceList, is(rsvInfoPriceService.getRsvInfoPrices(1)));
	}

	@Test		
	public void testGetRsvInfoPrices() {
		doNothing().when(rsvInfoPriceDao).insert(rsvInfoPriceList);
	}

}
