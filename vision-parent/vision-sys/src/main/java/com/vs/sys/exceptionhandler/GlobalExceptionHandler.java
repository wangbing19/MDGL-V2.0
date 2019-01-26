package com.vs.sys.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.vo.JsonResult;

/**
 * @ControllerAdvice 此注解描述的类 称之为全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult doHandleException(Exception e) {
		System.out.println("GlobalExceptionHandler.doHandleException");
		e.printStackTrace();
		return JsonResult.build(201, e.getMessage());
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public JsonResult doHandleThrowable(Throwable e) {
		e.printStackTrace();
		return  JsonResult.build(201, "系统维护中");
		
	}

}
