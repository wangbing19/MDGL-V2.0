package com.vs.res.servise;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.res.mapper.ResProjectConfigMapper;

import com.vs.vision.pojo.res.ResProjectConfig;
import com.vs.vision.vo.PageObject;
@Service
public class ResProjectConfigServiceImpl implements ResProjectConfigService{
	@Autowired
	ResProjectConfigMapper resProjectConfigMapper;

	@Override
	public PageObject<ResProjectConfig> dofindObjects(Map map) {
	
		Integer pageCurrent =(Integer) map.get("pageCurrent");
		Integer userId =(Integer) map.get("userId");
		//Integer count=resProjectConfigMapper.selectCount(userId);
				QueryWrapper<ResProjectConfig> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("user_id", userId);
		Integer count = resProjectConfigMapper.selectCount(queryWrapper);
		int pageSize=3;
        int startIndex=(pageCurrent-1)*pageSize;
        List<ResProjectConfig> records = resProjectConfigMapper.findResProjectConfigList(startIndex, pageSize);
        PageObject<ResProjectConfig> pageObject = new PageObject<ResProjectConfig>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        return pageObject;
		
	}
	
}
