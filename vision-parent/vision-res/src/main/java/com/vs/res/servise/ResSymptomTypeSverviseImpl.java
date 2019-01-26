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

}
