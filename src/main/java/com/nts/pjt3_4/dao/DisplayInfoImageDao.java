package com.nts.pjt3_4.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.DisplayInfoImageDto;

@Mapper
public interface DisplayInfoImageDao {

	public DisplayInfoImageDto selectBydisplayInfoId(@Param("displayInfoId") int displayInfoId);

}
