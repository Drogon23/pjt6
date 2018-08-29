package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;

@Mapper
public interface RsvInfoDao {

	public RsvInfoWithPriceDto select(@Param("rsvId") int rsvId);

	public List<RsvInfoDto> selectByEmail(@Param("rsvEmail") String rsvEmail);

	public int insertRsvInfo(RsvDto rsv);

	public float sumPrice(@Param("rsvId") int rsvId);
	
	public int updateCancelFlag(@Param("rsvId") int rsvId);

}
