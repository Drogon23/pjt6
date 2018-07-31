package com.nts.pjt3.sql;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "SELECT p.id, p.category_id, d.id AS displayInfoId, "
		+ "c.name, p.description, p.content, p.event, d.opening_hours, "
		+ "d.place_name, d.place_lot, d.place_street, d.tel, d.homepage, "
		+ "d.email, d.create_date, d.modify_date "
		+ "FROM product p "
		+ "INNER JOIN display_info d ON p.id = d.product_id "
		+ "INNER JOIN category c ON p.category_id = c.id "
		+ "ORDER BY p.id DESC limit :start, :limit";

	public static final String SELECT_BY_CATEGORY = "SELECT p.id, p.category_id, d.id AS displayInfoId, "
		+ "c.name, p.description, p.content, p.event, d.opening_hours, "
		+ "d.place_name, d.place_lot, d.place_street, d.tel, d.homepage, "
		+ "d.email, d.create_date, d.modify_date "
		+ "FROM product p "
		+ "INNER JOIN display_info d ON p.id = d.product_id "
		+ "INNER JOIN category c ON p.category_id = c.id "
		+ "WHERE p.category_id = :categoryId ORDER BY p.id DESC limit :start, :limit";

	public static final String SELECT_ONE = "SELECT p.id, p.category_id, d.id AS displayInfoId, "
		+ "c.name, p.description, p.content, p.event, d.opening_hours, "
		+ "d.place_name, d.place_lot, d.place_street, d.tel, d.homepage, "
		+ "d.email, d.create_date, d.modify_date "
		+ "FROM display_info d "
		+ "INNER JOIN product p ON p.id =d.product_id "
		+ "INNER JOIN category c ON p.category_id = c.id  WHERE d.id = :displayInfoId";

	public static final String COUNT_ALL_PRODUCTS = "SELECT COUNT(*) FROM product";

	public static final String COUNT_CATEGORY_PRODUCTS = "SELECT COUNT(*) FROM product WHERE category_id = :categoryId";
}
