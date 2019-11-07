package com.yucong.dto.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserIdDTO {

	@NotNull(message="用户ID必填")
	@ApiModelProperty(value = "用户ID",example = "1",required = true)
	private Long userId;
}
