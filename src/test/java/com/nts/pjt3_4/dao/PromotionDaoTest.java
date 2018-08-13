package com.nts.pjt3_4.dao;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3_4.config.ApplicationConfig;
import com.nts.pjt3_4.dao.PromotionDao;
import com.nts.pjt3_4.dto.PromotionDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class PromotionDaoTest {
	@Autowired
	private PromotionDao promotionDao;

	@Test
	public void testSelectAll() {
		List<PromotionDto> list = promotionDao.selectAll();
		assertThat(list.size(), greaterThan(0));
		System.out.println(list.get(0).toString());
	}

}
