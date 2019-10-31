package com.yucong.dto.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpderUser {

	@NotNull(message="memberUserId必填")
	@ApiModelProperty(value = "会员用户ID",example = "1",required = true)
	private Long id;
	
	@NotNull(message="memberUserId必填")
	@ApiModelProperty(value = "会员用户ID",example = "1",required = true)
	private String username;
	
	@NotNull(message="memberUserId必填")
	@ApiModelProperty(value = "会员用户ID",example = "1",required = true)
	private String password;
	
}
