package com.yucong.core.intercepter;

import java.lang.reflect.Method;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yucong.core.annotion.Auth;
import com.yucong.core.shiro.manager.PermissionCheckManager;

import lombok.extern.slf4j.Slf4j;


/**
 * 权限验证拦截器
 * 
 * @author 喻聪
 * @date   2018-01-25
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
    private PermissionCheckManager permissionCheckService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
	    	return true;
	    }
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler;  
        final Method method = handlerMethod.getMethod();  
        final Class<?> clazz = method.getDeclaringClass();
        
        boolean result = false;
        if (clazz.isAnnotationPresent(Auth.class) ||   method.isAnnotationPresent(Auth.class)) {  
        	
        	Auth auth = method.getAnnotation(Auth.class);
        	if(auth == null) {
        		auth = clazz.getAnnotation(Auth.class);
        	}
        	
            Object[] permissions = auth.value();
            if (permissions.length == 0) {

                //检查全体角色
                result = permissionCheckService.checkAll();
                if (!result) {
                	log.warn("没有访问权限...");
                    throw new NoPermissionException("没有访问权限");
                }

            } else {

                //检查指定角色
                result = permissionCheckService.check(permissions);
                if (!result) {
                    throw new NoPermissionException();
                }
            }
        } else {
        	result = true;
        } 
        return result;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
	
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
	
	}
	

}

