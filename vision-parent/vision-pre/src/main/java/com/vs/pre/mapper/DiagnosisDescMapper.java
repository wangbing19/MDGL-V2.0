package com.vs.pre.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.pre.DiagnosisDesc;
import com.vs.vision.vo.DiagnosisDate;

public interface DiagnosisDescMapper extends BaseMapper<DiagnosisDesc>{

	void updateObject(DiagnosisDate diagnosisDate);
	void insertObjectByDiagnosisId(@Param("diagnosisId")Integer diagnosisId);

}
