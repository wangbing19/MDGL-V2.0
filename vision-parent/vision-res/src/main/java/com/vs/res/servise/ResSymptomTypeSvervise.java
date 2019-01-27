package com.vs.res.servise;

import java.util.List;

import com.vs.vision.pojo.res.ResSymptomType;

public interface ResSymptomTypeSvervise {

	List<ResSymptomType> findObjects(Integer userId);
	/**课程表查询咨询表所有信息*/
	List<ResSymptomType> findSymptomType(Integer userId);
	ResSymptomType findPageObjectOne(ResSymptomType resSymptomType);
	Integer doupdateObject(ResSymptomType resSymptomType);
	Integer doSaveObject(ResSymptomType resSymptomType);
	Integer doDeleteObject(ResSymptomType resSymptomType);
	

}
