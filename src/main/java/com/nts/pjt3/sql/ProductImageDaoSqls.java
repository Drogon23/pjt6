package com.nts.pjt3.sql;

public class ProductImageDaoSqls {
	public static final String SELECT_BY_PRODUCT_ID = "SELECT pi.product_id, pi.id as productImageId, "
		+ "pi.type, pi.file_id as fileInfold, fi.file_name, fi.save_file_name, fi.content_type, "
		+ "fi.delete_flag, fi.create_date, fi.modify_date FROM product_image pi "
		+ "INNER JOIN file_info fi ON pi.file_id = fi.id WHERE pi.product_id = :productId AND pi.type = 'ma'";
}
