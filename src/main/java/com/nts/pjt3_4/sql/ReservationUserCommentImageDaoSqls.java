package com.nts.pjt3_4.sql;

public class ReservationUserCommentImageDaoSqls {
	public static final String SELECT_BY_COMMENT_ID = "SELECT rsvUserCommentImg.id, rsvUserCommentImg.reservation_info_id, rsvUserCommentImg.reservation_user_comment_id, rsvUserCommentImg.file_id, "
		+ "fileInfo.file_name, fileInfo.save_file_name, fileInfo.content_type, fileInfo.delete_flag, fileInfo.create_date, fileInfo.modify_date "
		+ "FROM reservation_user_comment_image rsvUserCommentImg "
		+ "INNER JOIN file_info fileInfo ON rsvUserCommentImg.file_id = fileInfo.id "
		+ "WHERE rsvUserCommentImg.reservation_user_comment_id = :commentId";
}
