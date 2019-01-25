package com.vs.vision.service;

import com.vs.vision.pojo.exp.SymptomsDescribed;

public interface SymptomsDescribedService {

	SymptomsDescribed selectSym(Integer id);

	Integer doInsertSym(SymptomsDescribed entity);

	Integer doUpdateSym(SymptomsDescribed entity);
	
	Integer doDeleteSym(Integer[] id);

}
