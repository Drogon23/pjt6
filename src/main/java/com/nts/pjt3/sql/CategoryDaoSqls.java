package com.nts.pjt3.sql;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT c.id, c.name, COUNT(c.id) AS count FROM category c "
		+ "INNER JOIN product p ON c.id = p.category_id GROUP BY c.id";
}
