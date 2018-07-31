package com.nts.pjt3.sql;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_BY_DISPLAYINFO_ID = "SELECT * from display_info_image d "
		+ "INNER JOIN file_info f ON d.file_id = f.id WHERE d.display_info_id = :displayInfoId";
}
