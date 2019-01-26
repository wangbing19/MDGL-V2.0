package com.vs.res.servise;

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

}
