package com.nts.pjt3.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3.config.ApplicationConfig;
import com.nts.pjt3.dto.DisplayInfoImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DisplayInfoImageDaoTest {
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	@Test
	public void testSelectBydisplayInfoId() {
		DisplayInfoImage displayInfoImage = displayInfoImageDao.selectBydisplayInfoId(1);
		System.out.println(displayInfoImage.toString());
		assertNotNull(displayInfoImage);
	}

}
