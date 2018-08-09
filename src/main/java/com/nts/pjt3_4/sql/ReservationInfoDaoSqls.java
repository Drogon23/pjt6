package com.nts.pjt3_4.sql;

public class ReservationInfoDaoSqls {
	public static final String SELECT_ONE = "SELECT id, product_id, reservation_name, reservation_tel, reservation_email, "
		+ "reservation_date, create_date, modify_date FROM reservation_info WHERE id = :reservationInfoId";
}
