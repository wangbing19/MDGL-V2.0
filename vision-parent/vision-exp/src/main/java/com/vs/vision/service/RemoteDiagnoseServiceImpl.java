package com.vs.vision.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.RemoteDiagnoseMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteDiagnoseServiceImpl implements RemoteDiagnoseService{

	@Autowired(required=true)
	private RemoteDiagnoseMapper remoteDiagnoseMapper;


	@Override
	public PageObject<ExpRemoteDiagnoseVo> findPageObjects(String customerName, Integer pageCurrent) {
		// 1.判断当前页是否合法
		if (pageCurrent == null || pageCurrent <= 0)
			throw new ServiceException("参数不合法");
		
		// 获取登录用户的账号
		//SysUser user=ShiroUtils.getUser(); 
		//System.out.println("user"+user);
		//Integer parentId = user.getParentId();
		// 2.依据条件获取总记录数
		
		int rowCount = remoteDiagnoseMapper.getRowCount(customerName);//parentId);
		//System.out.println("rowCount" + rowCount);
		// 3.判断记录是否存在
		if (rowCount == 0)
    		throw new ServiceException("您要查询记录不存在");

		// 4.计算每一页的开始下标
		int pageSize = 10;
		int startIndex = (pageCurrent - 1) * pageSize;

		// System.out.println("5555"+user.getParentId());

		// 5.依据条件获取当前页数据
		List<ExpRemoteDiagnoseVo> records = remoteDiagnoseMapper.findPageObjects(customerName, startIndex, pageSize);//1);
				//parentId);// 获取父级id
		//System.out.println("records=" + records);

		// 6.封装数据
		PageObject<ExpRemoteDiagnoseVo> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		return pageObject;
	}
}
