package com.vs.cus.service.impl;

import com.vs.cus.mapper.CusScheduleMapper;
import com.vs.cus.service.CusScheduleService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusScheduleServiceImpl implements CusScheduleService {
	
	@Autowired
	private CusScheduleMapper cusScheduleMapper;

	/**基于用户/电话及当前页码值条件查询课程信息*/
	@Override
	public PageObject<CusSchedule> findPageObjects(CusVo cusVo) {
		String name = cusVo.getName();
		if("".equals(name)) {
			name = null;
		}
		Integer pageCurrent = cusVo.getPageCurrent();
		int userId = cusVo.getUserId();
		int userParentId = cusVo.getUserParentId();
		
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("页码值不正确");
		//2.依据条件获取总记录数并进行验证
		int rowCount = cusScheduleMapper.getRowCount(name,userId,userParentId);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=10;
		int startIndex = (pageCurrent-1)*pageSize;
		List<CusSchedule> records =
				cusScheduleMapper.findPageObjects(
						name, startIndex, pageSize,userId,userParentId);
		//4.对查询结果进行封装并返回
		PageObject<CusSchedule> pageObject = 
				new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);

		return pageObject;
	}
	
}
