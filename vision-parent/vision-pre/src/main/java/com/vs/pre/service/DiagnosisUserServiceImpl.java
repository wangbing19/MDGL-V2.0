package com.vs.pre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.pre.mapper.DiagnosisUserMapper;
import com.vs.vision.pojo.pre.DiagnosisUser;

@Service
public class DiagnosisUserServiceImpl implements DiagnosisUserService{

	@Autowired
	private DiagnosisUserMapper diagnosisUserMapper;
	@Override
	public DiagnosisUser findUserIdIsExiste(Integer userId) {
		QueryWrapper<DiagnosisUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		DiagnosisUser selectOne = diagnosisUserMapper.selectOne(queryWrapper);
		System.out.println("后台查询是否存在的用户信息："+selectOne);
		return selectOne;
	}
	@Override
	public String deleteDescObjectByUserId(Integer userId) {
		QueryWrapper<DiagnosisUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",userId);
		int row = diagnosisUserMapper.delete(queryWrapper);
		return row==0?null:"删除成功";
	}
	@Override
	public String insertUserDiagbosisObject(Integer userId, Integer diagnosisId) {
		diagnosisUserMapper.insertUserDiagbosisObject(userId,diagnosisId);
		return "用户症状关系表新增成功";
	}
	@Override
	public String updateUserDiagbosisObject(Integer userId, Integer diagnosisId) {
		diagnosisUserMapper.updateUserDiagbosisObject(userId,diagnosisId);
		return "用户症状关系表更新成功";
	}
	
}
