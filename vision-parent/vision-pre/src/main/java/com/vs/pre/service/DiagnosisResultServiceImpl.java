package com.vs.pre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vs.pre.mapper.DiagnosisDescMapper;
import com.vs.pre.mapper.DiagnosisResultMapper;
import com.vs.pre.mapper.DiagnosisUserMapper;
import com.vs.vision.pojo.pre.DiagnosisDesc;
import com.vs.vision.pojo.pre.DiagnosisResult;
import com.vs.vision.pojo.pre.DiagnosisUser;
import com.vs.vision.vo.DiagnosisDate;
import com.vs.vision.vo.Node;
@Service
public class DiagnosisResultServiceImpl implements DiagnosisResultService{
	@Autowired
	private DiagnosisResultMapper diagnosisResultMapper;
	@Autowired
	private DiagnosisDescMapper diagnosisDescMapper;
	@Autowired
	private DiagnosisUserMapper diagnosisUserMapper;
	//查询症状表所有数据，初始化表格数据
	@Override
	public List<DiagnosisResult> doFindObjects() {
		System.out.println("我是实现类");
		return diagnosisResultMapper.selectList(null);
	}
	//根据症状id删除数据
	@Override
	public String deleteResultObjectById(Integer diagnosisId) {
		
		//判断删除的症状是否有子症状
		DiagnosisResult diagnosisResult = new DiagnosisResult();
		diagnosisResult.setParentId(diagnosisId);
		QueryWrapper<DiagnosisResult> queryWrapper1 = new QueryWrapper<>(diagnosisResult);	
		
		DiagnosisResult selectById = diagnosisResultMapper.selectById(diagnosisId);
		
		//查询需要删除的症状的父级症状id
		Integer count = diagnosisResultMapper.selectCount(queryWrapper1);
		System.out.println("需要删除的症状有"+count+"个子症状");
		if(count!=0) {
			return "请删除子症状";
		}
		//删除症状结果表数据
		diagnosisResultMapper.deleteById(diagnosisId);
		//删除症状描述表数据
		DiagnosisDesc diagnosisDesc = new DiagnosisDesc();
		diagnosisDesc.setDiagnosisId(diagnosisId);
		QueryWrapper<DiagnosisDesc> queryWrapper2 = new QueryWrapper<>(diagnosisDesc);	
		diagnosisDescMapper.delete(queryWrapper2);
		//删除用户症状关系表
		DiagnosisUser diagnosisUser = new DiagnosisUser();
		diagnosisUser.setDiagnosisId(diagnosisId);
		QueryWrapper<DiagnosisUser> queryWrapper3 = new QueryWrapper<>();
		diagnosisUserMapper.delete(queryWrapper3);
		
		//更新诊断处方显示状态
		QueryWrapper<DiagnosisResult> queryWrapper4 = new QueryWrapper<>();
		queryWrapper4.eq("parent_id",selectById.getParentId());
		Integer deleteAfterCount = diagnosisResultMapper.selectCount(queryWrapper4);
		System.out.println("删除后，对应的父级症状的子症状个数："+deleteAfterCount);
		if(deleteAfterCount==0) {
			diagnosisResultMapper.updateDisType(1,selectById.getParentId());
		}		
		return "删除成功";
	}
	//查询ztree节点信息
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> ztree =  diagnosisResultMapper.findZtreeMenuNodes();
		System.out.println("ztree节点数据："+ztree);
		return ztree;
	}
	@Override
	public String findParentNameById(Integer id) {
	
		DiagnosisResult selectById = diagnosisResultMapper.selectById(id);
		DiagnosisResult selectParentById = diagnosisResultMapper.selectById(selectById.getParentId());
		return selectParentById.getSymptomName();
		
	}
	@Override
	public String updateObject(DiagnosisDate diagnosisDate) {
		//更新症状结果表
		diagnosisResultMapper.updateObject(diagnosisDate);
		diagnosisDescMapper.updateObject(diagnosisDate);
		return "更新成功";
	}
	@Override
	public DiagnosisResult findObjectById(Integer id) {
		return diagnosisResultMapper.selectById(id);
	}
	@Override
	public String updateDisTypeById(Integer disType, Integer id) {
		diagnosisResultMapper.updateDisTypeById(disType,id);
		return "修改显示状态成功";
	}
	@Override
	public String insertObject(DiagnosisResult diagnosisResult) {
		diagnosisResultMapper.insertObject(diagnosisResult);
		diagnosisDescMapper.insertObjectByDiagnosisId(diagnosisResult.getId());
		return "添加成功";
	}
}













