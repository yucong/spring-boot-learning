package com.yucong.core.resolver;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yucong.core.annotion.CurrentUser;
import com.yucong.core.shiro.manager.UserAuthManager;
import com.yucong.entity.User;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserAuthManager userAuthManager;
	
    public CurrentUserMethodArgumentResolver() {
    
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	
    	// 取出的是username，在ShiroAuthRealm里放进去的
    	String username = (String) SecurityUtils.getSubject().getPrincipal();
    	User u = userAuthManager.findByUsername(username);
    	return u.getId();
    }
}
