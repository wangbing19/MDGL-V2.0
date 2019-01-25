package com.vs.vision.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.vision.mapper.ExpertReplyMapper;
import com.vs.vision.pojo.exp.ExpertReply;

@Service
public class ExpertReplyServiceImpl implements ExpertReplyService {
	
	@Autowired
	private ExpertReplyMapper expertReplyMapper;

	@Override
	public ExpertReply selectRep(Integer id) {
		QueryWrapper<ExpertReply> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("remote_diagnose_id", id);
		ExpertReply entity = expertReplyMapper.selectOne(queryWrapper);
		return entity;
	}

	
	@Override
	public Integer doInsertRep(ExpertReply entity) {
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		int in = expertReplyMapper.insert(entity);
		return in;
	}

	
	@Override
	public Integer doDeleteRep(Integer[] id) {
		List<Integer> ExpId = expertReplyMapper.selectExpId(id);
		for (Integer i : ExpId) {
			expertReplyMapper.deleteById(i);
		}
		
		return 1;
	}

	
	@Override
	public Integer doUpdateRep(ExpertReply entity) {
		entity.setGmtCreate(new Date());
		entity.setGmtModified(entity.getGmtCreate());
		List<Integer> id = expertReplyMapper.selectExpId(entity.getRemoteDiagnoseId());
		for (Integer i : id) {
			entity.setId(i);
		}
		int up = expertReplyMapper.updateById(entity);
		return up;
	}

}
