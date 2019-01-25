package com.vs.vision.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vs.vision.annoation.RequiresLog;
import com.vs.vision.pojo.sys.Logs;
import com.vs.vision.utils.IPUtils;
import com.vs.vision.vo.JsonResult;
import com.vs.vision.vo.sys.RestTemplateParmas;



@Aspect
@Service
public class SysLogAspect {
	
	private static final String sys_url = "http://localhost:8029/log";
    @Autowired
    private RestTemplate restTemplate;

	

	@Pointcut("@annotation(com.vs.vision.annoation.RequiresLog)")
	public void logPointCut() {
	}
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = jp.proceed();// 执行目标方法
		long end = System.currentTimeMillis();
		saveObject(jp, end - start);
		return result;
	}

	private void saveObject(ProceedingJoinPoint jp, long totalTime) throws NoSuchMethodException, SecurityException {
		// 1.获取日志信息
		//String username = ShiroUtils.getUser().getUsername();
		String username="admin";
		String ip = IPUtils.getIpAddr();
		// 获取方法签名信息(包含了方法名以及参数列表信息)
		Signature s = jp.getSignature();
		MethodSignature ms = (MethodSignature) s;
		// 获取目标对象的类对象(字节码对象：反射的起点)
		Class<?> targetCls = jp.getTarget().getClass();
		// 获取目标方法对象
		Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		// 获取目标方法上的RequiresLog注解
		RequiresLog requiresLog = targetMethod.getDeclaredAnnotation(RequiresLog.class);
		// 获取注解中value属性的值
		String operation = requiresLog.value();
		String method = targetCls.getName() + "." + targetMethod.getName();
		String params = Arrays.toString(jp.getArgs());
		// 2.封装日志信息(封装到SysLog对象)
		Logs log = new Logs();
		log.setUsername(username);
		log.setIp(ip);
		log.setOperation(operation);
		log.setMethod(method);
		log.setParams(params);
		log.setTime(totalTime);
		log.setCreatedTime(new Date());
		// 3.发送到后台-日志模块
		
		restTemplate.postForObject(sys_url + "/doInsertObjects", log, JsonResult.class);
		
	}
}
