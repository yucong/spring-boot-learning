package com.yucong.core.constant;

/**
 * 系统常量
 * 
 * */
public interface SystemConsts {

	 
	/**
     * 不需要权限验证的资源表达式
     */
    String[] NONE_PERMISSION_RES = {"/swagger-ui.html","/webjars/**", "/login" , "/swagger-resources/**" , "/v2/api-docs" , "/csrf","/"};
    
	 
}
