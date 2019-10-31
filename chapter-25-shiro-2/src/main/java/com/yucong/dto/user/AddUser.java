package com.yucong.dto.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddUser {

	@NotNull(message="username必填")
	@ApiModelProperty(value = "用户名",required = true)
	private String username;
	
	@NotNull(message="password必填")
	@ApiModelProperty(value = "密码",required = true)
	private String password;
	
}
