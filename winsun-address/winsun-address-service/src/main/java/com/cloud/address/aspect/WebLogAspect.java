package com.cloud.address.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
	private Logger logger =  LogManager.getLogger(WebLogAspect.class);
	
	@Pointcut("execution(public * com.cloud.address.controller..*.*(..))")
	public void webLog() {
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("************************请求开始*******************************");
		logger.info("执行线程 :" + Thread.currentThread().getName());
		logger.info("请求地址 : " + request.getRequestURL().toString());
		
		logger.info("请求方法 : " + request.getMethod());
		
		logger.info("IP : " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("参数:{},值:{}", name, request.getParameter(name));
			
		}
	}
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		if(ret.toString().length()<100){
			logger.info("响应结果 : " + ret);
		}
		logger.info("************************请求结束*******************************");
		
	}
}
