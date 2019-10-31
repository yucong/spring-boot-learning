package com.yucong.dto.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUser {

	@NotNull(message="用户ID必填")
	@ApiModelProperty(value = "用户ID",example = "1",required = true)
	private Long id;
	
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
}
