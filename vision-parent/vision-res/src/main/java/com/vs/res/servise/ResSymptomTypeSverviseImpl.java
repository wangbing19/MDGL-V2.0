package com.vs.res.servise;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.res.mapper.ResSymptomTypeMapper;
import com.vs.vision.pojo.res.ResSymptomType;
@Service
public class ResSymptomTypeSverviseImpl implements ResSymptomTypeSvervise{
	@Autowired
	ResSymptomTypeMapper resSymptomTypeMapper;
	
	@Override
	public List<ResSymptomType> findObjects(Integer userId) {
		
		QueryWrapper<ResSymptomType> queryWrapper=new QueryWrapper();
		queryWrapper.eq("user_id", userId);
		List<ResSymptomType> listresult = resSymptomTypeMapper.selectList(queryWrapper);
		return listresult;
	}

	/**课程表查询咨询表所有信息*/
	@Override
	public List<ResSymptomType> findSymptomType(Integer userId) {
		QueryWrapper<ResSymptomType> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).eq("choose_type", 1).eq("service_state", 1);
		List<ResSymptomType> list = resSymptomTypeMapper.selectList(queryWrapper);
		return list;
	}

	/**
	 * 根据id查询该配置表信息
	 */
	@Override
	public ResSymptomType findPageObjectOne(ResSymptomType resSymptomType) {
		
		Integer id = resSymptomType.getId();
		ResSymptomType result = resSymptomTypeMapper.selectById(id);
		return result;
	}

	@Override
	public Integer doupdateObject(ResSymptomType resSymptomType) {
		//System.out.println("业务层" + resSymptomType.toString());
		Integer id = resSymptomType.getId();
		 resSymptomTypeMapper.deleteById(id);
		 resSymptomType.setGmtModified(new Date());
		 resSymptomType.setGmtCreate(resSymptomType.getGmtModified());
		 int insert = resSymptomTypeMapper.insert(resSymptomType);
		// System.out.println("insert");
		return insert;
	}

	@Override
	public Integer doSaveObject(ResSymptomType resSymptomType) {
		resSymptomType.setGmtCreate(new Date());
		resSymptomType.setGmtModified(resSymptomType.getGmtCreate());
		int insert = resSymptomTypeMapper.insert(resSymptomType);
		return insert;
	}

	@Override
	public Integer doDeleteObject(ResSymptomType resSymptomType) {
		Integer id = resSymptomType.getId();
		int deleteById = resSymptomTypeMapper.deleteById(id);
		return deleteById;
	}

}
