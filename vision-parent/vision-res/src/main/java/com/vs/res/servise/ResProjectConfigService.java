package com.vs.res.servise;

import java.util.List;
import java.util.Map;

import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.PageObject;

public interface ResProjectConfigService {



	PageObject<ResProjectConfig> dofindObjects(Map map);

	Integer doSaveObject(ResProjectConfig entity);

	Integer doUpdate(ResProjectConfig entity);

	Integer doDelete(Integer[] ids);

	Integer doprojectStateById(Map map);

}
