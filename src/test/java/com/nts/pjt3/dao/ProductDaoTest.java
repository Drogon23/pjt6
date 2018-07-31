package com.nts.pjt3.dao;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.pjt3.config.ApplicationConfig;
import com.nts.pjt3.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ProductDaoTest {
	@Autowired
	ProductDao productDao;

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testSelectAll() {
		List<Product> list = productDao.selectAll(0, 4);
		assertThat(list.size(), greaterThan(0));
	}

	@Test
	public void testSelectByCategory() {
		List<Product> list = productDao.selectByCategory(0, 4, 1);
		assertThat(list.size(), greaterThan(0));
	}

	@Test
	public void testSelect() {
		Product product = productDao.select(1);
		assertNotNull(product);
	}

	@Test
	public void testCountAllProduct() {
		int count = productDao.countAllProduct();
		assertThat(count, greaterThan(0));
	}

	@Test
	public void testCountProductByCategory() {
		int count = productDao.countProductByCategory(1);
		assertThat(count, greaterThan(0));
	}

}
