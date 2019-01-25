package com.vs.pre.mapper;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vs.vision.pojo.pre.DiagnosisUser;

public interface DiagnosisUserMapper extends BaseMapper<DiagnosisUser>{

	void insertUserDiagbosisObject(@Param("userId")Integer userId, @Param("diagnosisId")Integer diagnosisId);

	void updateUserDiagbosisObject(@Param("userId")Integer userId, @Param("diagnosisId")Integer diagnosisId);

}
