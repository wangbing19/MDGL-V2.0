package com.vs.cus.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.cus.mapper.CusConsultationMapper;
import com.vs.cus.mapper.CusCustomerMapper;
import com.vs.cus.service.CusCustomerService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.CusCustomer;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.rec.RecPayUser;
import com.vs.vision.vo.PageObject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusCustomerServiceImpl implements CusCustomerService {

	@Autowired
	private CusCustomerMapper cusCustomerMapper;
	@Autowired
	private CusConsultationMapper cusConsultationMapper;

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

	/**将CusCustomer类型数据添加到数据库*/
	@Override
	public Integer saveObject(CusCustomer entity) {
		//验证数据合法性
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getTel()))
			throw new ServiceException("电话不能为空");
		if(StringUtils.isEmpty(entity.getGuardian()))
			throw new ServiceException("监护人不能为空");
		//保存数据
		/**设置状态*/
		entity.setState(1);
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		entity.setMoney(0.0);
		entity.setBalance(0.0);
		entity.setTotalTrainingTime(0);
		entity.setTimesOfTraining(0);
		entity.setRechargeCount(0);
		//建立咨询表对象并赋值
		CusConsultation consultation = new CusConsultation();
		consultation.setId(entity.getConsultationId());
		consultation.setName(entity.getName());
		consultation.setAge(entity.getAge());
		consultation.setGender(entity.getGender());
		consultation.setTel(entity.getTel());
		/**修改咨询表部分信息*/
		cusConsultationMapper.updateById(consultation);
		/**客户表新增*/
		int rows = cusCustomerMapper.insert(entity);
		//返回结果
		return rows;
	}

	/**基于id删除客户信息*/
	@Override
	public Integer deleteObject(Integer id) {
		//1.验证参数有效性
		if(id==null||id<1)
			throw new ServiceException("参数id无效");
		//2.删除当前菜单信息
		int rows = cusCustomerMapper.deleteById(id);
		if(rows==0)
			throw new ServiceException("此客户可能已经不存在");
		//4.删除菜单角色的关系数据
		//关联其他表项未做删除
		return rows;
	}

	/**基于客户id修改客户信息*/
	@Override
	public Integer updateObject(CusCustomer entity) {
		//验证数据合法性
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(entity.getId()<=0)
			throw new ServiceException("id错误");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("客户名不能为空");
		if(StringUtils.isEmpty(entity.getTel()))
			throw new ServiceException("电话不能为空");
		if(StringUtils.isEmpty(entity.getGuardian()))
			throw new ServiceException("监护人不能为空");
		//保存数据
		int rows = cusCustomerMapper.updateById(entity);
		//返回结果
		return rows;
	}

	/**基于用户id修改金额,余额及充值次数*/
	@Override
	public Integer updateObjectByMoney(RecPayUser recPayUser) {
		
		CusCustomer cusCustomer = new CusCustomer();
		CusCustomer customer = cusCustomerMapper.selectById(recPayUser.getCustomerId());
		//修改充值次数
		cusCustomer.setRechargeCount(customer.getRechargeCount()+1);
		//计算总金额
		double money = customer.getMoney();
		double rechargeAmount = recPayUser.getRechargeAmount();
		double presentedAmount = recPayUser.getPresentedAmount();
		money = money + rechargeAmount + presentedAmount;
		cusCustomer.setMoney(money);
		//计算余额
		double balance = customer.getBalance();
		balance = balance + rechargeAmount + presentedAmount;
		cusCustomer.setBalance(balance);
		//修改总训练次数
		Integer totalTrainingTime = customer.getTotalTrainingTime();
		totalTrainingTime = totalTrainingTime + recPayUser.getPracticeTimes();
		cusCustomer.setTotalTrainingTime(totalTrainingTime);
		//修改时间
		cusCustomer.setGmtModified(new Date());
		//根据id修改信息
		cusCustomer.setId(recPayUser.getCustomerId());
		int rows = cusCustomerMapper.updateById(cusCustomer);
		return rows;
	}

	/**基于训练记录表返回信息更改训练次数*/
	@Override
	public Integer updateObjectByTimesOfTraining(Integer customerId) {
		CusCustomer cusCustomer = new CusCustomer();
		CusCustomer customer = cusCustomerMapper.selectById(customerId);
		//获取训练次数并修改赋值
		cusCustomer.setTimesOfTraining(customer.getTimesOfTraining()+1);
		cusCustomer.setId(customerId);
		cusCustomer.setGmtCreate(new Date());
		int rows = cusCustomerMapper.updateById(cusCustomer);
		return rows;
	}
}
