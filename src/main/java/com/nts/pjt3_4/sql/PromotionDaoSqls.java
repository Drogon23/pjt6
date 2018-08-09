package com.nts.pjt3_4.sql;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT promotion.id, promotion.product_id, "
		+ "product.category_id, product.description, "
		+ "category.name as categoryName, "
		+ "product_image.id as productImageId FROM promotion "
		+ "INNER JOIN product ON promotion.product_id = product.id "
		+ "INNER JOIN category ON product.category_id = category.id "
		+ "INNER JOIN product_image ON product.id = product_image.product_id "
		+ "WHERE product_image.type = 'ma'";
}
