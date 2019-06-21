package com.yucong.constants;

/**
 * 系统常量
 * 
 * */
public interface ConfigConsts {

	String  DEFAUT_ENCODE = "UTF-8";
	
	String X_AUTH_TOKEN_KEY = "x-auth-token";
	 
	String X_USER_ID_KEY = "x-user-id";
	
	String DEVICE_TYPE_KEY = "deviceType";
	
	String APP_VERSION = "App-Version";
	
	/**系统token超时时间为7*24小时*/
	int TOKEN_EXPIRES_HOUR = 7*24;
	
	
    int SYSTEM_CONFIG_CACHE_TIME = 7*24;
	
	
	/**app请求签名的key*/
	String APP_KEY = "b9b8309ca3a04a7c906c67f6ed24ec9d";
	 
	 
	 
}
