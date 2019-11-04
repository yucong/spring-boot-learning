package com.yucong.core.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.util.StringUtils;
 
/**
 * 自定义sessionId获取
 */
public class MySessionManager extends DefaultWebSessionManager {
 
    // private static final String AUTHORIZATION = "Authorization";
	private static final String AUTHORIZATION = "token";
 
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
 
    public MySessionManager() {
        super();
    }
 
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
    	HttpServletRequest req =  (HttpServletRequest) request;
    	String sessionId = req.getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
