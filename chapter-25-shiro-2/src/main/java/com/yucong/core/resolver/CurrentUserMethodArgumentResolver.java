package com.yucong.core.resolver;

import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.yucong.core.annotion.CurrentUser;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

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
    	
    	// 取出的是userId，在ShiroAuthRealm里放进去的
    	Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
    	return userId;
    }
}
