package com.nts.pjt3_4.sql;

public class ProductPriceDaoSqls {
	public static final String SELECT_ALL_BY_PRODUCT_ID = "SELECT id, product_id, price_type_name, price, discount_rate, create_date, modify_date "
			+ "from product_price WHERE product_id = :productId ORDER BY price";
}
