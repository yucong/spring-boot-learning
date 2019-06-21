package com.yucong.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MySqlDTO {

	private String username;
	
	@NotNull(message = "page必填")
	private Integer page;
	
	@NotNull(message = "size必填")
	private Integer size;
}
