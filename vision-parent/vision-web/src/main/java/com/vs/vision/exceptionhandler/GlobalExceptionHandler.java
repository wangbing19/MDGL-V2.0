package com.vs.vision.exceptionhandler;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vs.vision.vo.JsonResult;

/**
 * @ControllerAdvice 此注解描述的类 称之为全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doHandleShiroException(ShiroException e) {
		e.printStackTrace();
		if (e instanceof UnknownAccountException) {
			return JsonResult.build(201, "用户不存在");
		} else if (e instanceof LockedAccountException) {
			return JsonResult.build(201, "账户已禁用");
		} else if (e instanceof IncorrectCredentialsException) {
			System.out.println("密码不正确");
			return JsonResult.build(201, "用户名或密码不正确");
		} else if (e instanceof AuthorizationException) {
			return JsonResult.build(201, "没有此操作权限");
		} else {
			return JsonResult.build(201, e.getMessage());
		}
	}

	/**
	 * @ExceptionHandler 对象描述的方法为一个异常处理方法
	 * @ExceptionHandler 内部定义的异常处理类型为本方法 可以处理的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult doHandleException(Exception e) {
		System.out.println("GlobalExceptionHandler.doHandleException");
		e.printStackTrace();
		// System.out.println(e instanceof ServiceException);
		return JsonResult.build(201, e.getMessage());// 封装异常
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public JsonResult doHandleThrowable(Throwable e) {
		e.printStackTrace();
		return  JsonResult.build(201, "系统维护中");
		
	}

}
