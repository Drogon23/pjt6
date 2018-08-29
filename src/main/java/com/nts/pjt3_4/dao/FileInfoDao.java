package com.nts.pjt3_4.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nts.pjt3_4.dto.FileInfoDto;

@Mapper
public interface FileInfoDao {

	public void insert(FileInfoDto fileInfo);
}
