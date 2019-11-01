package com.yucong.vo.user;

import lombok.Data;

@Data
public class LoginVO {
	
	private Long userId;
	private String username;
	private String nickName = "admin";
	private String tokenId = "tokenId";
	
}
