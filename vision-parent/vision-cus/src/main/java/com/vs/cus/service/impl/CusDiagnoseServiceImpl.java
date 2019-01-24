package com.vs.cus.service.impl;

import com.vs.cus.mapper.CusDiagnoseMapper;
import com.vs.cus.service.CusDiagnoseService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusDiagnose;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusDiagnoseServiceImpl implements CusDiagnoseService {

	@Autowired
	private CusDiagnoseMapper cusDiagnoseMapper;

	/**诊断表页面加载,查询*/
	@Override
	public PageObject<CusDiagnose> findPageObjects(CusVo cusVo) {
		Integer userId = cusVo.getUserId();
		Integer userParentId = cusVo.getUserParentId();
		Integer pageCurrent = cusVo.getPageCurrent();
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("页码值不正确");
		//2.依据条件获取总记录数并进行验证
		int rowCount = cusDiagnoseMapper.getRowCount(userId, userParentId);
		//	System.out.println(rowCount);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=10;
		int startIndex = (pageCurrent-1)*pageSize;
		List<CusDiagnose> records = cusDiagnoseMapper.findPageObjects(userId, userParentId, startIndex, pageSize);
		//4.对查询结果进行封装并返回
		PageObject<CusDiagnose> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);

		/**页数设置，在com.md.common.vo.PageObject<T>中的getPageCount更改返回值
		 *  int pageCount=(rowCount-1)/pageSize+1;
		 *	pageObject.setPageCount(pageCount);
		 */
		return pageObject;
	}

	/**基于咨询表id,查询相关id所有信息*/
	@Override
	public CusDiagnose findObjectById(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("id错误");
		//执行查找
		CusDiagnose cusDiagnose = cusDiagnoseMapper.selectById(id);
		return cusDiagnose;
	}
}
