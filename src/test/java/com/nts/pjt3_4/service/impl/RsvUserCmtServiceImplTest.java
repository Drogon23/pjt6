package com.nts.pjt3_4.service.impl;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nts.pjt3_4.dao.FileInfoDao;
import com.nts.pjt3_4.dao.RsvUserCmtDao;
import com.nts.pjt3_4.dao.RsvUserCmtImgDao;
import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvUserCmtDto;

@RunWith(MockitoJUnitRunner.class)
public class RsvUserCmtServiceImplTest {

	@Mock
	private RsvUserCmtDao rsvUserCmtDao;
	@Mock
	private FileInfoDao fileInfoDao;
	@Mock
	private RsvUserCmtImgDao rsvUserCmtImgDao;
	@Mock
	private ServletContext context;
	@InjectMocks
	private RsvUserCmtServiceImpl rsvUserCmtService;
	private List<RsvUserCmtDto> commentList;
	
	@Before
	public void setUp() throws Exception {
		commentList = new ArrayList<>();
		RsvUserCmtDto comment = new RsvUserCmtDto(1,1,1,"comment");
		commentList.add(comment);
		commentList.add(comment);
		commentList.add(comment);
		
	}
	

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testGetThreeComments() {
		given(rsvUserCmtDao.selectAll(1, 0, 3)).willReturn(commentList);
		assertThat(commentList.get(0).getId(), is(rsvUserCmtService.getThreeComments(1, 0).get(0).getId()));
		assertThat(3, is(rsvUserCmtService.getThreeComments(1, 0).size()));
	}

	@Test
	public void testReserveRegex() {
		String regexName = "^[가-힣]*$|^[a-zA-Z]*";
		String regexPhone = "^\\d{3}-\\d{3,4}-\\d{4}$";
		String regexEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String phone = "000-101-0111";
		String email = "mark@nts-corp.com";
		String name = "mark";
		assertThat(true, is(Pattern.matches(regexName, name)));
		assertThat(true, is(Pattern.matches(regexPhone, phone)));
		assertThat(true, is(Pattern.matches(regexEmail, email)));
	}

	@Test
	public void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAvgScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavedFileName() {
		fail("Not yet implemented");
	}

	@Test
	public void testImgFileSave() {
		fail("Not yet implemented");
	}

}
