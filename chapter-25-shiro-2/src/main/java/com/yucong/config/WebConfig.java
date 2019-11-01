package com.yucong.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.yucong.core.intercepter.AuthInterceptor;
import com.yucong.core.resolver.CurrentUserMethodArgumentResolver;


/**
 * 配置类
 * 
 * @author 喻聪
 * @date   2018-01-26
 */
@Configuration
@ServletComponentScan 
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired  
	private AuthInterceptor loginInterceptor;  
	
	@Autowired
    private CurrentUserMethodArgumentResolver handlerMethodArgumentResolver;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	/** 
     * 拦截器配置 
     */  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        // 注册监控拦截器  
        registry.addInterceptor(loginInterceptor)  
                .addPathPatterns("/**")  
         .excludePathPatterns("/configuration/ui")
         .excludePathPatterns("/swagger-ui.html")
         .excludePathPatterns("/webjars/**");
    }  
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(handlerMethodArgumentResolver);
    }
	
}
