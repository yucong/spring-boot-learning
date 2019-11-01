package com.yucong.dto.login;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDTO {

	@NotNull(message="登录名必填")
	@ApiModelProperty(value = "用户名",required = true)
	private String username;
	
	@NotNull(message="密码必填")
	@ApiModelProperty(value = "密码",required = true)
	private String password;
	
	
}
