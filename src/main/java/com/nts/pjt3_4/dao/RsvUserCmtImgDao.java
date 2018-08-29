package com.nts.pjt3_4.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.RsvUserCmtImgDto;

@Mapper
public interface RsvUserCmtImgDao {

	public RsvUserCmtImgDto selectByCommentId(@Param("commentId") int commentId);

	public int insert(@Param("rsvInfoId") int rsvInfoId, @Param("rsvUserCmtId") int rsvUserCmtId,
		@Param("fileId") int fileId);
}
