package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.CommentDto;
import com.nts.pjt3_4.dto.RsvUserCmtDto;

@Mapper
public interface RsvUserCmtDao {

	public List<RsvUserCmtDto> selectAll(@Param("productId") int productId, @Param("start") int start,
		@Param("limit") int limit);

	public int countByProductId(@Param("productId") int productId);

	public float avgScore(@Param("productId") int productId);

	public int insert(CommentDto comment);
}
