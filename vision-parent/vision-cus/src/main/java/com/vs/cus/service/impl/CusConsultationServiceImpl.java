package com.vs.cus.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.vs.cus.mapper.CusConsultationMapper;
import com.vs.cus.service.CusConsultationService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusConsultationVo;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CusConsultationServiceImpl implements CusConsultationService {
	@Autowired
	private CusConsultationMapper cusConsultationMapper;


	/**基于用户/电话及当前页码值条件查询用户信息*/
	@Override
	public PageObject<CusConsultation> findPageObjects(CusConsultationVo cusConsultationVo) {

		String name = cusConsultationVo.getName();
		if("".equals(name)) {
			name = null;
		}
		String tel = cusConsultationVo.getTel();
		Integer pageCurrent = cusConsultationVo.getPageCurrent();
		//	System.out.println(pageCurrent);
		int userId = cusConsultationVo.getUserId();
		int userParentId = cusConsultationVo.getUserParentId();

		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("页码值不正确");
		//2.依据条件获取总记录数并进行验证
		int rowCount = cusConsultationMapper.getRowCount(name, tel, userId, userParentId);
		//	System.out.println(rowCount);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=10;
		int startIndex = (pageCurrent-1)*pageSize;
		List<CusConsultation> records =
				cusConsultationMapper.findPageObjects(name, tel,startIndex, pageSize,userId, userParentId);// userId, userParentId ,
		//4.对查询结果进行封装并返回
		PageObject<CusConsultation> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);

		/*	页数设置，在com.md.common.vo.PageObject<T>中的getPageCount更改返回值
		 *  int pageCount=(rowCount-1)/pageSize+1;
		 *	pageObject.setPageCount(pageCount);
		 */	
		return pageObject;
	}


	/**将CusCustomer类型数据添加到数据库*/
	@Override
	public Integer saveObject(CusConsultation entity) {
		//验证数据合法性
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getTel()))
			throw new ServiceException("电话不能为空");
		//封装信息
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		//保存数据
		Integer rows = cusConsultationMapper.insert(entity);

		//返回结果
		return rows;
	}

	/**基于id删除咨询表信息*/
	@Override
	public Integer deleteObject(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("请选择一条数据");
		//执行删除
		int rows = cusConsultationMapper.deleteById(id);
		//判断数据有无
		if(rows==0)
			throw new ServiceException("数据可能已删除");
		return rows;
	}

	/**基于咨询表id,查询相关id所有信息*/
	@Override
	public CusConsultation findObjectById(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("id错误");
		//执行删除
		CusConsultation cusConsultation = cusConsultationMapper.selectById(id);
		return cusConsultation;
	}

	/**基于咨询表id更改用户信息*/
	@Override
	public Integer updateObject(CusConsultation entity) {
		//验证数据
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(entity.getId()==0)
			throw new ServiceException("对象id不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getTel()))
			throw new ServiceException("电话不能为空");
		//执行
		entity.setFOther(null);
		int rows = cusConsultationMapper.updateObject(entity);
		if(rows==0)
			throw new ServiceException("修改失败");
		return rows;
	}
}
