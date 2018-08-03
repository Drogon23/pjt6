package com.nts.pjt3.sql;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "SELECT product.id, product.category_id, product.description, "
		+ "product.content, product.event, "
		+ "category.name, "
		+ "display_info.id AS displayInfoId, display_info.opening_hours, "
		+ "display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel, display_info.homepage, "
		+ "display_info.email, display_info.create_date, display_info.modify_date "
		+ "FROM display_info "
		+ "INNER JOIN product ON product.id = display_info.product_id "
		+ "INNER JOIN category ON product.category_id = category.id "
		+ "ORDER BY product.id ASC limit :start, :limit";

	public static final String SELECT_BY_CATEGORY = "SELECT product.id, product.category_id, product.description, "
		+ "product.content, product.event, "
		+ "category.name, "
		+ "display_info.id AS displayInfoId, display_info.opening_hours, "
		+ "display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel, display_info.homepage, "
		+ "display_info.email, display_info.create_date, display_info.modify_date "
		+ "FROM display_info "
		+ "INNER JOIN product ON product.id = display_info.product_id "
		+ "INNER JOIN category ON product.category_id = category.id "
		+ "WHERE product.category_id = :categoryId ORDER BY product.id ASC limit :start, :limit";

	public static final String SELECT_ONE = "SELECT product.id, product.category_id, product.description, "
		+ "product.content, product.event, "
		+ "category.name, "
		+ "display_info.id AS displayInfoId, display_info.opening_hours, "
		+ "display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel, display_info.homepage, "
		+ "display_info.email, display_info.create_date, display_info.modify_date "
		+ "FROM display_info "
		+ "INNER JOIN product ON product.id = display_info.product_id "
		+ "INNER JOIN category ON product.category_id = category.id  WHERE display_info.id = :displayInfoId";

	public static final String COUNT_ALL_PRODUCTS = "SELECT COUNT(*) FROM display_info";

	public static final String COUNT_PRODUCTS_CATEGORY = "SELECT COUNT(*) FROM display_info "
		+ "INNER JOIN product ON display_info.product_id = product.id WHERE category_id = :categoryId";
}
