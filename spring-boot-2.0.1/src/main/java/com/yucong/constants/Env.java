package com.yucong.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Env {

	public static Boolean isProduct;
	
	@Value("${env.isProduct}")
	public void setProduct(Boolean isProduct){
		Env.isProduct = isProduct;
	}



}
