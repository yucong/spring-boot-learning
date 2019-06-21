package com.yucong.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring boot整合mysql和druid
 * https://blog.csdn.net/sinat_32366329/article/details/84404944
 * 
 */
@Configuration
public class DruidConfiguration {

	@Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne(){
		System.err.println("dataSouce初始化。。。");
        return DruidDataSourceBuilder.create().build();
    }
	
	@Bean
    public ServletRegistrationBean<StatViewServlet> statViewServle() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(
				new StatViewServlet(), "/druid/*");
        //IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,172.25.20.108");
        //IP黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.20.208,192.168.20.209");
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "12345678");
        //是否允许重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

	@Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(
        		new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js, *.jpg, *.png, *.css, /druid/*");
        return filterRegistrationBean;
    }

}
