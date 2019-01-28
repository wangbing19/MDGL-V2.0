package com.vs.res.servise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vs.res.mapper.ResProjectConfigMapper;
import com.vs.vision.pojo.exp.RemoteDiagnose;
import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.PageObject;
@Service
public class ResProjectConfigServiceImpl implements ResProjectConfigService{
	@Autowired
	ResProjectConfigMapper resProjectConfigMapper;

	@Override
	public PageObject<ResProjectConfig> dofindObjects(Map map) {
	
		Integer pageCurrent =(Integer) map.get("pageCurrent");
		String projectName = (String)map.get("projectName");
		Integer userId =(Integer) map.get("userId");
		int count = resProjectConfigMapper.getRowCount(projectName,userId);
		int pageSize=10;
        int startIndex=(pageCurrent-1)*pageSize;
        List<ResProjectConfig> records = resProjectConfigMapper.findResProjectConfigList(startIndex, pageSize);
        PageObject<ResProjectConfig> pageObject = new PageObject<ResProjectConfig>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        return pageObject;
		
	}

	@Override
	public Integer doSaveObject(ResProjectConfig entity) {
		int in = resProjectConfigMapper.insert(entity);
		return in;
	}

	@Override
	public Integer doUpdate(ResProjectConfig entity) {
		int up = resProjectConfigMapper.updateById(entity);
		return up;
	}

	@Override
	public Integer doDelete(Integer[] ids) {
		List<Integer> list = new ArrayList<>();
		for (Integer id : ids) {
			list.add(id);
		}
		int de = resProjectConfigMapper.deleteBatchIds(list);
		return de;
	}

	@Override
	public Integer doprojectStateById(Map map) {
		Integer id = (Integer)map.get("id");
		Integer projectState = (Integer)map.get("projectState");
		
		ResProjectConfig entity = new ResProjectConfig();
		entity.setProjectState(projectState);
		entity.setGmtModified(new Date());
		//entity.setId(id);
		UpdateWrapper<ResProjectConfig> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id",id);


		Integer i = resProjectConfigMapper.update(entity, updateWrapper);
		return i;
	}
	
}
