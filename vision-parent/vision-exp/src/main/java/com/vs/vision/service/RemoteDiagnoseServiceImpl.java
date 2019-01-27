package com.vs.vision.service;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.RemoteDiagnoseMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RemoteDiagnoseServiceImpl implements RemoteDiagnoseService{

	@Autowired
	private RemoteDiagnoseMapper remoteDiagnoseMapper;
	
	@Autowired
	private ExpertReplyService expertReplyService;
	
	@Autowired
	private SymptomsDescribedService symptomsDescribedService;

	/**分页*/
	@Override
	public PageObject<ExpRemoteDiagnoseVo> findPageObjects(String customerName, Integer pageCurrent,Integer parentId) {
		// 1.判断当前页是否合法
		if (pageCurrent == null || pageCurrent <= 0)
			throw new ServiceException("参数不合法");

		// 获取登录用户的账号
		//SysUser user=ShiroUtils.getUser(); 
		//System.out.println("user"+user);
		//Integer parentId = user.getParentId();
		// 2.依据条件获取总记录数

		int rowCount = remoteDiagnoseMapper.getRowCount(customerName,parentId);
		//System.out.println("rowCount" + rowCount);
		// 3.判断记录是否存在
		if (rowCount == 0)
			throw new ServiceException("您要查询记录不存在");

		// 4.计算每一页的开始下标
		int pageSize = 10;
		int startIndex = (pageCurrent - 1) * pageSize;

		// System.out.println("5555"+user.getParentId());

		// 5.依据条件获取当前页数据
		List<ExpRemoteDiagnoseVo> records = remoteDiagnoseMapper.findPageObjects(customerName, startIndex, pageSize//1);
		,parentId);// 获取父级id
		//System.out.println("records=" + records);

		// 6.封装数据
		PageObject<ExpRemoteDiagnoseVo> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		return pageObject;
	}

	/**通过id获取要修改的数据在页面显示*/
	@Override
	public ExpRemoteDiagnoseVo select(Integer id) {

		return remoteDiagnoseMapper.select(id);
	}

	/**修改解决状态*/
	@Override
	public Integer validById(Integer id,Integer valid,String modifyuser) {


		RemoteDiagnose entity = new RemoteDiagnose();
		entity.setValid(valid);
		entity.setModifiedUser(modifyuser);
		//entity.setId(id);
		UpdateWrapper<RemoteDiagnose> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",id);


		Integer i = remoteDiagnoseMapper.update(entity, updateWrapper);
		return i;
	}

	/**删除*/
	@Override
	public Integer doDelete(Integer[] ids) {
		List<Integer> list = new ArrayList<>();

		for(int i=0;i<ids.length;i++) {
			list.add(ids[i]);
		}
		int is = remoteDiagnoseMapper.deleteBatchIds(list);
		
		
		Integer rep = expertReplyService.doDeleteRep(ids);
		
		Integer sym = symptomsDescribedService.doDeleteSym(ids);
		return is;
	}

	/**添加*/
	@Override
	public Integer doSaveObject(RemoteDiagnose entity) {
		// 1.对参数进行校验
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getDeptSite()))
			throw new IllegalArgumentException("门店地址不能为空");
		if (StringUtils.isEmpty(entity.getDeptName()))
			throw new IllegalArgumentException("门店名称不能为空");
		if (StringUtils.isEmpty(entity.getCustomerName()))
			throw new IllegalArgumentException("客户姓名不能为空");
		if (StringUtils.isEmpty(entity.getCustomerTel()))
			throw new IllegalArgumentException("客户电话不能为空");
		if (StringUtils.isEmpty(entity.getSendName()))
			throw new IllegalArgumentException("发送人姓名不能为空");
		if (StringUtils.isEmpty(entity.getSendTel()))
			throw new IllegalArgumentException("发送人电话不能为空");
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		
	//	Integer in = remoteDiagnoseMapper.insertObject(entity);
		//entity.setId(50);
		int in = remoteDiagnoseMapper.insert(entity);
		return in;
	}

	/**将修改后的数据放入数据库*/
	@Override
	public Integer doUpdate(RemoteDiagnose entity) {
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getDeptSite()))
			throw new IllegalArgumentException("门店地址不能为空");
		if (StringUtils.isEmpty(entity.getDeptName()))
			throw new IllegalArgumentException("门店名称不能为空");
		if (StringUtils.isEmpty(entity.getCustomerName()))
			throw new IllegalArgumentException("客户姓名不能为空");
		if (StringUtils.isEmpty(entity.getCustomerTel()))
			throw new IllegalArgumentException("客户电话不能为空");
		if (StringUtils.isEmpty(entity.getSendName()))
			throw new IllegalArgumentException("发送人姓名不能为空");
		if (StringUtils.isEmpty(entity.getSendTel()))
			throw new IllegalArgumentException("发送人电话不能为空");
		int up = remoteDiagnoseMapper.updateById(entity);
		return up;
	}
}
