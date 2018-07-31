package com.nts.pjt3.sql;

public class ProductPriceDaoSqls {
	public static final String SELECT_ALL_BY_PRODUCT_ID = "SELECT * from product_price WHERE product_id = :productId ORDER BY price";
}
