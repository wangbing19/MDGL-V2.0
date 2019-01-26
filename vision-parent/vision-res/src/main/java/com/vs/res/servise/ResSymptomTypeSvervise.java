package com.vs.res.servise;

import java.util.List;

import com.vs.vision.pojo.res.ResSymptomType;

public interface ResSymptomTypeSvervise {

	List<ResSymptomType> findObjects(Integer userId);

}
