package com.nts.pjt3.sql;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT pro.id, pro.product_id, p.category_id, c.name as categoryName, "
		+ "p.description, img.id as productImageId FROM promotion pro INNER JOIN product p ON pro.product_id = p.id "
		+ "INNER JOIN category c ON p.category_id = c.id INNER JOIN product_image img ON p.id = img.product_id "
		+ "WHERE img.type = 'ma'";
}
