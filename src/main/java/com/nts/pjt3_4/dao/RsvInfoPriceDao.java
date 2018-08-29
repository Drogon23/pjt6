package com.nts.pjt3_4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.pjt3_4.dto.RsvInfoPriceDto;

@Mapper
public interface RsvInfoPriceDao {

	public List<RsvInfoPriceDto> select(@Param("rsvInfoId") int rsvInfoId);

	public int insert(List<RsvInfoPriceDto> rsvInfoPrices);
}
