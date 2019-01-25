package com.vs.vision.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.ExpertMapper;
import com.vs.vision.pojo.exp.ExpRemoteDiagnoseVo;
import com.vs.vision.pojo.exp.Expert;
import com.vs.vision.vo.Node;
import com.vs.vision.vo.PageObject;

@Service
public class ExpertServiceImpl implements ExpertService {
	
	@Autowired
	private ExpertMapper expertMapper;

	@Override
	public Node[] selectExpName() {
		
		return expertMapper.selectExpName();
	}

	
	/**
	 * 通过获取的专家姓名和当前页数进行分页查询
	 */
	@Override
	public PageObject<Expert> limitExp(String expertName, Integer pageCurrent) {
		//1.判断当前页码值
		if(pageCurrent<1||pageCurrent==null)
			throw new ServiceException("请输入正确的页码值");
		//2.查询总记录数
		int rowCount = expertMapper.countExp(expertName);
		//System.out.println("rowCount="+rowCount);
		/*if(rowCount==0)
			throw new ServiceException("您要查询的记录不存在");*/
		
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;

		//3.依据条件获取当前页数据
		List<Expert> records=
				expertMapper.limitExp(expertName, startIndex, pageSize);
		//System.out.println("records="+records);

		//4.封装数据
		PageObject<Expert> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);//当前页码值
		pageObject.setRowCount(rowCount);//总记录数
		pageObject.setPageSize(pageSize);//每一页显示条数
		pageObject.setRecords(records);//当前页显示数据
		return pageObject;
	}

	/**查看要修改的数据*/
	@Override
	public Expert doSelectExp(Integer id) {
		QueryWrapper<Expert> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", id);
		Expert entity = expertMapper.selectOne(queryWrapper);
		return entity;
	}


	/**修改数据*/
	@Override
	public Integer doUpdateExp(Expert entity) {
		int uo = expertMapper.updateById(entity);
		return uo;
	}


	/**删除*/
	@Override
	public Integer doDeleteExp(Integer[] ids) {
		List<Integer> list = new ArrayList<>();

		for(int i=0;i<ids.length;i++) {
			list.add(ids[i]);
		}
		int is = expertMapper.deleteBatchIds(list);
		
		return is;
	}

	/**添加*/
	@Override
	public Integer doSaveObject(Expert entity) {
		if(entity==null)
			throw new ServiceException("数据不能为空");
		if(StringUtils.isEmpty(entity.getExpertName()))
			throw new ServiceException("专家姓名不能为空");
		if(StringUtils.isEmpty(entity.getExpertTel()))
			throw new ServiceException("专家电话不能为空");
		if(StringUtils.isEmpty(entity.getAppointmentTime()))
			throw new ServiceException("专家预约时间不能为空");
		//执行添加
		int count = expertMapper.insert(entity);
		
		return count;
	}


	@Override
	public Integer doUpdateMessage(Expert entity) {
		
		int ub = expertMapper.updateById(entity);
		return ub;
	}

}
