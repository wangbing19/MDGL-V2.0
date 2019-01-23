package com.vs.cus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.cus.mapper.CusConsultationMapper;
import com.vs.cus.service.CusConsultationService;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.cus.CusConsultation;
import com.vs.vision.pojo.cus.vo.CusConsultationVo;
import com.vs.vision.vo.PageObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusConsultationServiceImpl implements CusConsultationService {
    @Autowired
    private CusConsultationMapper cusConsultationMapper;

    
    /**基于用户/电话及当前页码值条件查询用户信息*/
	@Override
	public PageObject<CusConsultation> findPageObjects(CusConsultationVo cusConsultationVo) {
		
		String name = cusConsultationVo.getName();
		if("".equals(name)) {
			name = null;
		}
        Integer pageCurrent = cusConsultationVo.getPageCurrent();
        System.out.println(pageCurrent);
        
        
      //1.数据合法性验证
      		if(pageCurrent==null||pageCurrent<=0)
      			throw new ServiceException("页码值不正确");
      		//2.依据条件获取总记录数并进行验证
      		QueryWrapper<CusConsultation> queryWrapper = new QueryWrapper<>();
      		queryWrapper.eq("name", name);//.eq("tel", tel)
      		int rowCount = cusConsultationMapper.selectCount(null);
      		if(name != null) {
      			rowCount = cusConsultationMapper.selectCount(queryWrapper);
      		}
      				//getRowCount(name,tel);
      	//	System.out.println(rowCount);
      		if(rowCount==0)
      			throw new ServiceException("记录不存在");
      		//3.基于条件查询当前页记录
      		int pageSize=10;
      		int startIndex = (pageCurrent-1)*pageSize;
      		List<CusConsultation> records =
      				cusConsultationMapper.findPageObjects(name, startIndex, pageSize);
      		//4.对查询结果进行封装并返回
      		PageObject<CusConsultation> pageObject = new PageObject<>();
      		pageObject.setRowCount(rowCount);
      		pageObject.setRecords(records);
      		pageObject.setPageCurrent(pageCurrent);
      		pageObject.setPageSize(pageSize);

      		/*	页数设置，在com.md.common.vo.PageObject<T>中的getPageCount更改返回值
      		 *  int pageCount=(rowCount-1)/pageSize+1;
      		 *	pageObject.setPageCount(pageCount);
      		 */	
      		
      		return pageObject;
	}
}
