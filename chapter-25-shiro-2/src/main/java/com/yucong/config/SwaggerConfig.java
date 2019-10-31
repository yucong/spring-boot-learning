package com.yucong.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	//是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "true")
	Boolean swaggerEnabled;

	@Bean
	public Docket createRestApi() {
		
		List<Parameter> pars = new ArrayList<>();
        pars.add(new ParameterBuilder()
        		.name("App-Version")
        		.description("客户端版本")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .defaultValue("1.0.0")
                .hidden(true)
                .build());
        pars.add(new ParameterBuilder()
        		.name("Device-Type")
        		.description("设备类型")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .defaultValue("app")
                .hidden(true)
                .build());
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled).select()
				// 扫描的路径包
				//.apis(RequestHandlerSelectors.basePackage("com.crm.controller"))
				//这里采用包含注解的方式来确定要显示的接口
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	//设置api信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("后台接口系统")
				.description("当前版本：1.0.0\n" +
                        "\n" +
                        "\t*头部header的参数*\n" +
                        "\t1、X-Auth-Token\t\t用户token(如:38661703ac5a4d9582c2c7839040719a)\n" +
                        "\t2、Device-Type\t\t设备类型（如：app）\n" +
                        "\t3、App-Version\t\t客户端版本（如：1.0.0）") 
				// 作者信息
				//.contact(new Contact("yucong", "https://blog.xxx.com/", "498077-@qq.com"))
				.version("1.0.0")
				.build();
	}
}
