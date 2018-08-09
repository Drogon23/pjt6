package com.nts.pjt3_4.sql;

public class ReservationUserCommentDaoSqls {
	public static final String SELECT_ALL = "SELECT id, product_id, reservation_info_id, score, comment, create_date, modify_date "
		+ "FROM reservation_user_comment WHERE product_id = :productId "
		+ "ORDER BY id DESC LIMIT :start, :limit";
	public static final String COUNT_BY_PRODUCT_ID = "SELECT COUNT(*) FROM reservation_user_comment WHERE product_id = :productId";
	public static final String AVG_SCORE = "SELECT AVG(score) FROM reservation_user_comment WHERE product_id = :productId";
}
