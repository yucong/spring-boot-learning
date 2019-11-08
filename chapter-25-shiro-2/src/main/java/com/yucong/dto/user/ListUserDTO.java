package com.yucong.dto.user;

import com.yucong.core.base.dto.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListUserDTO extends PageInfo {

	private String username;
	
}
