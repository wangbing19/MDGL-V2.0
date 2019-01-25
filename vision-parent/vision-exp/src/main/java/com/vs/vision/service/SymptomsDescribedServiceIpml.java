package com.vs.vision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.mapper.SymptomsDescribedMapper;
import com.vs.vision.pojo.exp.SymptomsDescribed;

@Service
public class SymptomsDescribedServiceIpml implements SymptomsDescribedService {

	@Autowired
	private SymptomsDescribedMapper symptomsDescribedMapper;
	
	/**通过id查询症状描述表中的数据*/
	@Override
	public SymptomsDescribed selectSym(Integer id) {
		QueryWrapper<SymptomsDescribed> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("remote_diagnose_id", id);
		SymptomsDescribed entity = symptomsDescribedMapper.selectOne(queryWrapper);
		return entity;
	}

	/**症状描述表添加数据*/
	@Override
	public Integer doInsertSym(SymptomsDescribed entity) {
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		int in = symptomsDescribedMapper.insert(entity);
		return in;
	}
	
	
	/**症状描述表添加数据*/
	@Override
	public Integer doUpdateSym(SymptomsDescribed entity) {
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		UpdateWrapper<SymptomsDescribed> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("remote_diagnose_id", entity.getRemoteDiagnoseId());
		int in = symptomsDescribedMapper.update(entity, updateWrapper);
		return in;
	}
	
	
	/**症状描述表删除数据*/
	@Override
	public Integer doDeleteSym(Integer... ids) {
		List<Integer> SymId = symptomsDescribedMapper.selectSymId(ids);
		for (Integer i : SymId) {
			symptomsDescribedMapper.deleteById(i);
		}
		
		return 1;
	}
}
