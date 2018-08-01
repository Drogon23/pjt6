package com.nts.pjt3.sql;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT category.id, category.name, COUNT(display_info.id) AS count FROM category "
		+ "INNER JOIN product ON category.id = product.category_id "
		+ "INNER JOIN display_info ON display_info.product_id = product.id GROUP BY category.id, category.name";
}
