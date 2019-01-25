package com.vs.pre.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.vo.DiagnosisDate;
import com.vs.vision.vo.Node;

public interface DiagnosisResultMapper extends BaseMapper<DiagnosisResult>{

	void updateDisType(@Param("disType")Integer disType,@Param("id")Integer id);
	List<Node> findZtreeMenuNodes();
	void updateObject(DiagnosisDate diagnosisDate);
	void updateDisTypeById(@Param("disType")Integer disType,@Param("id")Integer id);
	void insertObject(DiagnosisResult diagnosisResult);

}
