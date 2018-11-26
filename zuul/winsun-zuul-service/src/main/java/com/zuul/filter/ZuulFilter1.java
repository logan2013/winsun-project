package com.zuul.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulFilter1 extends ZuulFilter{
	
	

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
//		RequestContext ctx = null;
//		try {
//			ctx = RequestContext.getCurrentContext();
//			HttpServletResponse response = ctx.getResponse();
//			HttpServletRequest request = ctx.getRequest();
//			Enumeration<String> headerNames = request.getHeaderNames();
//			while (headerNames.hasMoreElements()) {
//				String name = headerNames.nextElement();
//				Enumeration<String> values = request.getHeaders(name);
//	
//				while (values.hasMoreElements()) {
//					String value = values.nextElement();
//					ctx.addZuulRequestHeader(name, value);
//				}
//			}
//		} catch (Exception e) {
//			ctx.setSendZuulResponse(false);
//		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
