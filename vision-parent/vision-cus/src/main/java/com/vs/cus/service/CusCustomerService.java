package com.vs.cus.service;

import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

public interface CusCustomerService {

	/**用户页面查看所有信息*/
	PageObject<CusCustomer> findPageObjects(CusVo cusVo);
	/**基于客户id查询客户所有信息*/
	CusCustomer findObjectById(Integer id);
}
