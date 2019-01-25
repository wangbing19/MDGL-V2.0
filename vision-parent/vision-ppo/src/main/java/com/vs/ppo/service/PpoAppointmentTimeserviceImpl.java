package com.vs.ppo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vs.ppo.mapper.PpoAppointmentTimeMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.ppo.PpoAppointmentTime;
import com.vs.vision.pojo.ppo.vo.PpoAppTime;
import com.vs.vision.vo.JsonResult;

@Service
public class PpoAppointmentTimeserviceImpl implements PpoAppointmentTimeservice{
	@Autowired
	private PpoAppointmentTimeMapper ppoAppointmentTimeMapper;

	/**字符串转换时间格式方法*/
	public Date dateFormat(String strDate) {
	
		strDate=strDate.replace("T", " ");
		//System.out.println("00000000000000"+strDate);
		//strDate=strDate.replace("-", "\\");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parseTime = null;
		try {
			parseTime = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseTime;
	}
	
	/**时间转换字符串格式方法*/
	public String formatDate(Date strDate) {
	
		//strDate=strDate.replace("T", " ");
		//System.out.println("00000000000000"+strDate);
		//strDate=strDate.replace("-", "\\");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String parseTime = null;
		parseTime = formatter.format(strDate);
		parseTime=parseTime.replace(" ","T");
		System.out.println("66666666666"+parseTime);
		return parseTime;
	}
	@Override
	public Integer saveTime(PpoAppTime ppoAppTime) {
		
	 PpoAppointmentTime ppoAppointmentTime =new PpoAppointmentTime();
	// System.out.println("11111111111111"+dateFormat(ppoAppTime.getStartTime()));
	 //System.out.println("22222222222222"+dateFormat(ppoAppTime.getEndTime()));
	 ppoAppointmentTime.setStartTime(dateFormat(ppoAppTime.getStartTime()));
	 ppoAppointmentTime.setEndTime(dateFormat(ppoAppTime.getEndTime()));
	 ppoAppointmentTime.setGmtCreate(new Date());
	 ppoAppointmentTime.setGmtModified(ppoAppointmentTime.getGmtCreate());
	 int insert = ppoAppointmentTimeMapper.insert(ppoAppointmentTime);
		return insert;
	}
	@Override
	public Integer updateTime(PpoAppTime ppoAppTime) {
		//通过用户Id删除数据
		Integer appointmentId = ppoAppTime.getAppointmentId();
		QueryWrapper<PpoAppointmentTime> queryWrapper = new QueryWrapper();
		queryWrapper.eq("appointment_id", appointmentId);
		 ppoAppointmentTimeMapper.delete(queryWrapper);
		
		//从新添加该训练师的训练时间
		PpoAppointmentTime ppoAppointmentTime =new PpoAppointmentTime();
		ppoAppointmentTime.setStartTime(dateFormat(ppoAppTime.getStartTime()));
		 ppoAppointmentTime.setEndTime(dateFormat(ppoAppTime.getEndTime()));
		 ppoAppointmentTime.setGmtCreate(new Date());
		 ppoAppointmentTime.setGmtModified(ppoAppointmentTime.getGmtCreate());
		 int insert = ppoAppointmentTimeMapper.insert(ppoAppointmentTime);
		
		return insert;
	}
	@Override
	public List<PpoAppTime> findTime(PpoAppTime ppoAppTime) {
		PpoAppTime ppoAppTimes=new PpoAppTime();
		List<PpoAppTime> result=new ArrayList<>();
		Integer appointmentId = ppoAppTime.getAppointmentId();
		QueryWrapper<PpoAppointmentTime> queryWrapper = new QueryWrapper();
		queryWrapper.eq("appointment_id", appointmentId);
		List<PpoAppointmentTime> selectList = ppoAppointmentTimeMapper.selectList(queryWrapper);
		for (PpoAppointmentTime ppoAppointmentTime : selectList) {
			ppoAppTimes.setStartTime(formatDate(ppoAppointmentTime.getStartTime()));
			ppoAppTimes.setEndTime(formatDate(ppoAppointmentTime.getEndTime()));
			ppoAppTimes.setAppointmentId(ppoAppointmentTime.getAppointmentId());
			result.add(ppoAppTimes);
			
		}
		return result;
	}

	
	
}
