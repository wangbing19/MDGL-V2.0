package com.vs.rec.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.rec.mapper.ActivityPushMapper;
import com.vs.vision.pojo.rec.RecActivityPush;

@Service
public class ActivityPushServiceImpl implements ActivityPushService {
	@Autowired
	private ActivityPushMapper activityPushMapper;

	@Override
	public List<RecActivityPush> findObjects(Integer activityState) {
		if(activityState==null) return activityPushMapper.selectList(null);
		else {
			QueryWrapper<RecActivityPush> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("activity_state",activityState);
			return activityPushMapper.selectList(queryWrapper);
		}
	}

	@Override
	public String deleteObjectById(Integer id) {
		int row = activityPushMapper.deleteById(id);
		if(row==1) {
			return "删除成功";
		}else {
			return "删除失败";
		}
	}

	@Override
	public String insertRecActivityObject(RecActivityPush recActivityPush) {
		
		recActivityPush.setGmtCreate(new Date());
		recActivityPush.setGmtModified(recActivityPush.getGmtCreate());
		recActivityPush.setActivityState(1);
		
		try {
			activityPushMapper.insert(recActivityPush);
			return "新增成功";
		} catch (Exception e) {
			
		}
		return "新增失败";
	}

	@Override
	public String updateRecActivityObject(RecActivityPush recActivityPush) {
		recActivityPush.setGmtModified(new Date());
		recActivityPush.setActivityState(1);
		try {
			activityPushMapper.updateById(recActivityPush);
			return "更新成功";
		} catch (Exception e) {
			
		}
		return "更新失败";
	}

	@Override
	public RecActivityPush findPageObjectByTitle(String title) {
		QueryWrapper<RecActivityPush> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("title", title);
		RecActivityPush selectOne = activityPushMapper.selectOne(queryWrapper);
		return selectOne;
	}
}

















