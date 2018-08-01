package com.nts.pjt3.sql;

public class ProductImageDaoSqls {
	public static final String SELECT_BY_PRODUCT_ID = "SELECT product_image.product_id, product_image.id as productImageId, "
		+ "product_image.type, product_image.file_id as fileInfold, "
		+ "file_info.file_name, file_info.save_file_name, file_info.content_type, "
		+ "file_info.delete_flag, file_info.create_date, file_info.modify_date FROM product_image "
		+ "INNER JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.product_id = :productId AND product_image.type = 'ma'";
}
