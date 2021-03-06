package com.vs.vision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.TraInformationrecordMapper;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusVo;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.vo.PageObject;

@Service
public class TraInformationrecordServiceImpl implements TraInformationrecordService {
	@Autowired
	private TraInformationrecordMapper traInformationrecordMapper;

	/**分页*/
	@Override
	public PageObject<TraInformationrecord> findPageObjects(CusVo cusVo) {

		String name = cusVo.getName();
		if("".equals(name)) {
			name = null;
		}
		//	String tel = cusVo.getTel();
		Integer pageCurrent = cusVo.getPageCurrent();
		//	System.out.println(pageCurrent);
		Integer userId = cusVo.getUserId();
		Integer userParentId = cusVo.getUserParentId();

		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("页码值不正确");
		//2.依据条件获取总记录数并进行验证
		int rowCount = traInformationrecordMapper.getRowCount(name, userId, userParentId);
		//	System.out.println(rowCount);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=10;
		int startIndex = (pageCurrent-1)*pageSize;
		List<TraInformationrecord> records =
				traInformationrecordMapper.findPageObjects(name, startIndex, pageSize,userId, userParentId);// userId, userParentId ,
		//4.对查询结果进行封装并返回
		PageObject<TraInformationrecord> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);

		/*	页数设置，在com.md.common.vo.PageObject<T>中的getPageCount更改返回值
		 *  int pageCount=(rowCount-1)/pageSize+1;
		 *	pageObject.setPageCount(pageCount);
		 */	
		return pageObject;

		//		
		//		// 1.判断当前页是否合法
		//		if (pageCurrent == null || pageCurrent <= 0)
		//			throw new ServiceException("参数不合法");
		//
		//		// 获取登录用户的账号
		//		//SysUser user=ShiroUtils.getUser(); 
		//		//System.out.println("user"+user);
		//		//Integer parentId = user.getParentId();
		//		// 2.依据条件获取总记录数
		//		System.out.println("tra_informationrecord="+userParentId);
		//		
		//		int rowCount = traInformationrecordMapper.getRowCount(name,userParentId);
		//		//System.out.println("rowCount" + rowCount);
		//		// 3.判断记录是否存在
		//		if (rowCount == 0)
		//			throw new ServiceException("您要查询记录不存在");
		//
		//		// 4.计算每一页的开始下标
		//		int pageSize = 10;
		//		int startIndex = (pageCurrent - 1) * pageSize;
		//
		//		// System.out.println("5555"+user.getParentId());
		//
		//		// 5.依据条件获取当前页数据
		//		List<TraInformationrecord> records = traInformationrecordMapper.findPageObjects(name, startIndex, pageSize//1);
		//		,userParentId);// 获取父级id
		//		//System.out.println("records=" + records);
		//
		//		// 6.封装数据
		//		PageObject<TraInformationrecord> pageObject = new PageObject<>();
		//		pageObject.setPageCurrent(pageCurrent);
		//		pageObject.setRowCount(rowCount);
		//		pageObject.setPageSize(pageSize);
		//		pageObject.setRecords(records);
		//		return pageObject;
	}


	/**添加训练记录到数据库*/
	@Override
	public Integer doSaveObject(TraInformationrecord entity) {
		//验证数据合法性
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		entity.setEndTime(entity.getGmtCreate());

		int rows = traInformationrecordMapper.insert(entity);
		return rows;
	}


	/**删除*/
	@Override
	public Integer doDeleteObject(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("请选择一条数据");
		//执行删除
		int rows = traInformationrecordMapper.deleteById(id);
		//判断数据有无
		if(rows==0)
			throw new ServiceException("数据可能已删除");
		return rows;
	}

	/**通过id查询*/
	@Override
	public TraInformationrecord doSelect(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("请选择一条数据");
		TraInformationrecord traInformationrecord = traInformationrecordMapper.selectId(id);
		return traInformationrecord;
	}


	/**通过id修改训练表信息*/
	@Override
	public Integer doUpdate(TraInformationrecord entity) {
		if(entity==null)
			throw new ServiceException("对象不能为空");
		if(entity.getId()==0)
			throw new ServiceException("对象id不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		int rows = traInformationrecordMapper.updateById(entity);
		return rows;
	}
}
