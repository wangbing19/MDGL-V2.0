package com.vs.pre.service;

import com.vs.vision.pojo.pre.DiagnosisUser;

public interface DiagnosisUserService {

	DiagnosisUser findUserIdIsExiste(Integer userId);

	String deleteDescObjectByUserId(Integer userId);

	String insertUserDiagbosisObject(Integer userId, Integer diagnosisId);

	String updateUserDiagbosisObject(Integer userId, Integer diagnosisId);

	void downLoadUpdate();

}
