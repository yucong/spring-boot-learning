package com.yucong.config;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 参考博客：https://blog.csdn.net/ao10001/article/details/80931510
 *
 */
@EnableConfigurationProperties(BeetlProperties.class)
@Configuration
public class MyBeetlConfig {
	
	@Autowired
    private BeetlProperties beetlProperties;
    
    /**
     * beetl的配置
     */
    @Bean(initMethod = "init")
    public BeetlConfiguration beetlConfiguration() {
        BeetlConfiguration beetlConfiguration = new BeetlConfiguration();
        beetlConfiguration.setResourceLoader(new ClasspathResourceLoader(MyBeetlConfig.class.getClassLoader(), beetlProperties.getPrefix()));
        beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
        return beetlConfiguration;
    }

    /**
     * beetl的视图解析器
     */
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver() {
    	BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlConfiguration());
        return beetlSpringViewResolver;
    }
    
}

