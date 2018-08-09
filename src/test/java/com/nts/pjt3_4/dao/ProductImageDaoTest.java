package com.nts.pjt3_4.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3_4.config.ApplicationConfig;
import com.nts.pjt3_4.dao.ProductImageDao;
import com.nts.pjt3_4.dto.ProductImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ProductImageDaoTest {
	@Autowired
	private ProductImageDao productImageDao;

	@Test
	public void testSelect() {
		ProductImage productImage = productImageDao.selectMainImageByProductId(1);
		assertNotNull(productImage);
		System.out.println(productImage.toString());
	}

}
