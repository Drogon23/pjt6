package com.nts.pjt3.sql;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_BY_DISPLAYINFO_ID = "SELECT display_info_image.id, display_info_image.display_info_id, display_info_image.file_id, "
		+ "file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date "
		+ "from display_info_image "
		+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id WHERE display_info_image.display_info_id = :displayInfoId";
}
