package com.vs.vision.service;

import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.vo.PageObject;

public interface TraInformationrecordService {
	/**训练记录分页及姓名查询*/
	PageObject<TraInformationrecord> findPageObjects(CusVo cusVo);
	/**添加训练记录到数据库*/
	Integer doSaveObject(TraInformationrecord entity);
	/**删除*/
	Integer doDeleteObject(Integer id);
	/**通过id查询*/
	TraInformationrecord doSelect(Integer id);
	/**通过id修改训练表信息*/
	Integer doUpdate(TraInformationrecord entity);


}
