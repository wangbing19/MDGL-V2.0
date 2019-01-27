package com.vs.vision.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.pra.TraInformationrecord;

public interface TraInformationrecordMapper extends BaseMapper<TraInformationrecord>{
	
	List<TraInformationrecord> findPageObjects(
			@Param("name") String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize,
			@Param("userParentId")Integer userParentId);
	
	
	int getRowCount(
			@Param("name") String name,
			@Param("userParentId")Integer userParentId);
}
