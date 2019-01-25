package com.vs.pre.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.vs.pre.service.DiagnosisUserService;
import com.vs.vision.pojo.pre.DiagnosisUser;

//准备订单定时任务
@Component
public class DownLoadQuartz extends QuartzJobBean{
	
	@Autowired
	private DiagnosisUserService diagnosisUserService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		diagnosisUserService.downLoadUpdate();
		System.out.println("定时任务执行");
	}
}
