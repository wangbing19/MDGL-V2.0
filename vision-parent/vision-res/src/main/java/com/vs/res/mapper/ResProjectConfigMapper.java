package com.vs.res.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.res.ResProjectConfig;

public interface ResProjectConfigMapper extends BaseMapper<ResProjectConfig>{

	List<ResProjectConfig> findResProjectConfigList(
												@Param("startIndex") int startIndex,
												@Param("pageSize") int pageSize);
	
	
	int getRowCount(
			@Param("projectName") String projectName,
			@Param("userId")Integer userId);
	
}
