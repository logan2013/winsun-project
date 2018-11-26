package com.cloud.oauth2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.oauth2.utils.IPUtils;

import javassist.compiler.ast.Symbol;

@Component
public class URLInterceptor implements HandlerInterceptor{

	private static final Logger log =  LogManager.getLogger(HandlerInterceptor.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String ipAddr = IPUtils.getIpAddr(request);
		log.info("ipAddr:{}",ipAddr);
		
		String remoteAddr = request.getRemoteAddr();
		log.info("remoteAddr:{}",remoteAddr);
        return false;
    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {	
	}
	
}
