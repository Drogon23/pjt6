package com.nts.pjt3_4.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3_4.config.ApplicationConfig;
import com.nts.pjt3_4.dao.DisplayInfoImageDao;
import com.nts.pjt3_4.dto.DisplayInfoImageDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DisplayInfoImageDaoTest {
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	@Test
	public void testSelectBydisplayInfoId() {
		DisplayInfoImageDto displayInfoImage = displayInfoImageDao.selectBydisplayInfoId(1);
		System.out.println(displayInfoImage.toString());
		assertNotNull(displayInfoImage);
	}

}
