package com.yucong.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

public class MyPassThruAuthenticationFilter extends PassThruAuthenticationFilter {

	
	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
			// System.err.println("options 请求无需登录验证");
			return true;
		}
		return super.onPreHandle(request, response, mappedValue);
	}
	
}
