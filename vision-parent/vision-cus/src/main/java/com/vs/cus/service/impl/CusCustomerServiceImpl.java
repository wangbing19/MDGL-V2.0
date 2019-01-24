package com.vs.cus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.cus.mapper.CusCustomerMapper;
import com.vs.cus.service.CusCustomerService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusCustomerServiceImpl implements CusCustomerService {

	@Autowired
	private CusCustomerMapper cusCustomerMapper;

	/**用户页面查看所有信息*/
	@Override
	public PageObject<CusCustomer> findPageObjects(CusVo cusVo) {
		
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
		int rowCount = cusCustomerMapper.getRowCount(name,userId,userParentId);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=10;
		int startIndex = (pageCurrent-1)*pageSize;
		List<CusCustomer> records =
				cusCustomerMapper.findPageObjects(
						name, startIndex, pageSize,userId,userParentId);
		//4.对查询结果进行封装并返回
		PageObject<CusCustomer> pageObject = 
				new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		
		return pageObject;
	}

	/**基于客户id查询客户所有信息*/
	@Override
	public CusCustomer findObjectById(Integer id) {
		if(id<=0||id==null)
			throw new ServiceException("id错误");
		CusCustomer cusCustomer = cusCustomerMapper.selectById(id);
		return cusCustomer;
	}

	/**基于用户id修改用户状态*/
	@Override
	public Integer updateStateById(CusVo cusVo) {
		CusCustomer cusCustomer = new CusCustomer();
		Integer id = cusVo.getId();
		Integer state = cusVo.getState();
		
		if(id<=0||id==null)
			throw new ServiceException("id错误");
		if(state!=0 && state!=1)
			throw new ServiceException("状态错误");
		
		cusCustomer.setId(id);
		cusCustomer.setState(state);
		/**获取登陆用户,还未写*/
		cusCustomer.setModifiedUser(cusVo.getUser());
		cusCustomer.setGmtModified(new Date());
		
		int rows = cusCustomerMapper.updateById(cusCustomer);
		return rows;
	}

	/**根据咨询表id查询客户表信息有无*/
	@Override
	public Integer findConsultationByConsultationId(Integer consultationId) {
		if(consultationId<=0||consultationId==null)
			throw new ServiceException("consultationId错误");
		QueryWrapper<CusCustomer> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("consultation_id", consultationId);
		Integer rows = cusCustomerMapper.selectCount(queryWrapper);
		return rows;
		
	}
}
