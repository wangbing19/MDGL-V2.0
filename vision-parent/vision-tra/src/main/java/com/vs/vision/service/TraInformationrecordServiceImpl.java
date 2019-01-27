package com.vs.vision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.TraInformationrecordMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.pra.TraInformationrecord;
import com.vs.vision.vo.PageObject;

@Service
public class TraInformationrecordServiceImpl implements TraInformationrecordService {
	@Autowired
	private TraInformationrecordMapper traInformationrecordMapper;
	
	/**分页*/
	@Override
	public PageObject<TraInformationrecord> findPageObjects(String name, Integer pageCurrent,Integer userParentId) {
		// 1.判断当前页是否合法
		if (pageCurrent == null || pageCurrent <= 0)
			throw new ServiceException("参数不合法");

		// 获取登录用户的账号
		//SysUser user=ShiroUtils.getUser(); 
		//System.out.println("user"+user);
		//Integer parentId = user.getParentId();
		// 2.依据条件获取总记录数
		System.out.println("tra_informationrecord="+userParentId);
		
		int rowCount = traInformationrecordMapper.getRowCount(name,userParentId);
		//System.out.println("rowCount" + rowCount);
		// 3.判断记录是否存在
		if (rowCount == 0)
			throw new ServiceException("您要查询记录不存在");

		// 4.计算每一页的开始下标
		int pageSize = 10;
		int startIndex = (pageCurrent - 1) * pageSize;

		// System.out.println("5555"+user.getParentId());

		// 5.依据条件获取当前页数据
		List<TraInformationrecord> records = traInformationrecordMapper.findPageObjects(name, startIndex, pageSize//1);
		,userParentId);// 获取父级id
		//System.out.println("records=" + records);

		// 6.封装数据
		PageObject<TraInformationrecord> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		return pageObject;
	}

	
	/**添加*/
	@Override
	public Integer doSaveObject(TraInformationrecord entity) {
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		
		int in = traInformationrecordMapper.insert(entity);
		return in;
	}


	/**删除*/
	@Override
	public Integer doDeleteObject(Integer id) {
		return traInformationrecordMapper.deleteById(id);
	}

	/**通过id查询*/
	@Override
	public TraInformationrecord doSelect(Integer id) {
		return traInformationrecordMapper.selectById(id);
	}


	/**修改*/
	@Override
	public Integer doUpdate(TraInformationrecord entity) {
		int up = traInformationrecordMapper.updateById(entity);
		return up;
	}
}
