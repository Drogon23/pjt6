package com.nts.pjt3.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3.config.ApplicationConfig;
import com.nts.pjt3.dto.ProductImage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ProductImageDaoTest {
	@Autowired
	ProductImageDao productImageDao;

	@Test
	public void testSelect() {
		ProductImage productImage = productImageDao.selectByProductId(1);
		assertNotNull(productImage);
		System.out.println(productImage.toString());
	}

}