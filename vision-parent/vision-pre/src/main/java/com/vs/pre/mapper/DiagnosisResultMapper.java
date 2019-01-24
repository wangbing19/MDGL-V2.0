package com.vs.pre.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.pre.DiagnosisResult;

public interface DiagnosisResultMapper extends BaseMapper<DiagnosisResult>{

	void updateDisType(@Param("id")Integer id);

}
