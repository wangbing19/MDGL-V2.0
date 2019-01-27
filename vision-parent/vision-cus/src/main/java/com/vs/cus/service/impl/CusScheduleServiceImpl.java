package com.vs.cus.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.cus.mapper.CusResScheduleMapper;
import com.vs.cus.mapper.CusScheduleMapper;
import com.vs.cus.service.CusScheduleService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusResSchedule;
import com.vs.vision.pojo.cus.CusSchedule;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.vo.PageObject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusScheduleServiceImpl implements CusScheduleService {

	@Autowired
	private CusScheduleMapper cusScheduleMapper;
	@Autowired
	private CusResScheduleMapper cusResScheduleMapper;
	

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

	/**基于id删除课程信息*/
	@Override
	public Integer deleteObject(Integer id) {
		//验证数据
		if(id==null||id<=0)
			throw new ServiceException("请选择一条数据");
		//执行删除
		int rows = cusScheduleMapper.deleteById(id);
		//删除课程表与资源配置表(训练项目表)的关系表
		deleteCusResSchedule(id);
		
		//判断数据有无
		if(rows==0)
			throw new ServiceException("数据可能已删除");
		return rows;
	}

	/**基于id查询课程信息*/
	@Override
	public CusSchedule findObjectById(Integer id) {
		//验证数据
		if(id==null||id<=0)
			throw new ServiceException("请选择一条数据");
		//执行基于id查找课程表信息
		CusSchedule cusSchedule = cusScheduleMapper.selectById(id);
		
		//基于课程表查询训练项目信息
		QueryWrapper<CusResSchedule> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("cus_schedule_id", id);
		List<CusResSchedule> list = cusResScheduleMapper.selectList(queryWrapper);
		//遍历list存入数组
		Integer[] symptomTypes = new Integer[list.size()];
		for(int i = 0; i <list.size() ;i++) {
			symptomTypes[i] = list.get(i).getResSymptomId();
		}
		
		cusSchedule.setSymptomTypes(symptomTypes);
	//	System.out.println(cusSchedule.toString());
		return cusSchedule;
	}

	/**创建客户课程表*/
	@Override
	public Integer saveObject(CusSchedule cusSchedule) {
	//	System.out.println(cusSchedule.toString());
		//验证数据合法性
		if(cusSchedule==null)
			throw new ServiceException("对象不能为空");
		if(StringUtils.isEmpty(cusSchedule.getName()))
			throw new ServiceException("用户名不能为空");
		if(cusSchedule.getSymptomTypes()==null||cusSchedule.getSymptomTypes().length==0)
			throw new ServiceException("课程表训练项目不能为空");
		//保存数据
		cusSchedule.setGmtCreate(new Date());
		cusSchedule.setGmtModified(cusSchedule.getGmtCreate());
		//保存课程表数据
		int rows = cusScheduleMapper.insert(cusSchedule);
		//更改用户表课程数量信息
		
		//循环遍历保存课程表训练项目id
		for (Integer resSymptomId : cusSchedule.getSymptomTypes()) {

			CusResSchedule cusResSchedule = new CusResSchedule();
			cusResSchedule.setCusScheduleId(cusSchedule.getId());
			cusResSchedule.setResSymptomId(resSymptomId);
			cusResScheduleMapper.insert(cusResSchedule);
		}
		//返回结果
		return rows;
	}

	/**修改课程表数据*/
	@Override
	public Integer updateObject(CusSchedule cusSchedule) {
	//	System.out.println(cusSchedule.toString());
		//验证数据合法性
		if(cusSchedule==null)
			throw new ServiceException("对象不能为空");
		if(StringUtils.isEmpty(cusSchedule.getName()))
			throw new ServiceException("用户名不能为空");
		if(cusSchedule.getSymptomTypes()==null||cusSchedule.getSymptomTypes().length==0)
			throw new ServiceException("课程表训练项目不能为空");
		//保存数据
		cusSchedule.setGmtModified(new Date());
		int rows = cusScheduleMapper.updateById(cusSchedule);
		
		//删除课程表与资源配置表(训练项目表)的关系表
		deleteCusResSchedule(cusSchedule.getId());
		for (Integer resSymptomId : cusSchedule.getSymptomTypes()) {

			CusResSchedule cusResSchedule = new CusResSchedule();
			cusResSchedule.setCusScheduleId(cusSchedule.getId());
			cusResSchedule.setResSymptomId(resSymptomId);
			cusResScheduleMapper.insert(cusResSchedule);
		}
		//返回结果
		return rows;
	}
	
	/**删除课程表与资源配置表(训练项目表)的关系表*/
	public void deleteCusResSchedule(Integer cusScheduleId) {
		QueryWrapper<CusResSchedule> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("cus_schedule_id", cusScheduleId);
		cusResScheduleMapper.delete(queryWrapper);
	}

	/**基于客户id查询用户课程表信息*/
	@Override
	public List<CusSchedule> findByCustomerId(Integer id) {
		QueryWrapper<CusSchedule> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("customer_id", id);
		List<CusSchedule> list = cusScheduleMapper.selectList(queryWrapper);
		return list;
	}



}
