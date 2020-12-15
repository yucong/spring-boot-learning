package com.yucong.dto;

// import com.fasterxml.jackson.databind.PropertyNamingStrategy;
// import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddUserDTO {

	private String username;
	private String password;
	private String headUrl;
	
}
