package com.yucong.dto.menu;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class AddMenuDTO {

	@NotNull(message="parentId必填")
	public Long parentId;
	//@NotBlank
	private String name;
	private String type;
	//@NotBlank
	private String permission;
	//@NotBlank
	private String scode;
	//@NotNull
	private Integer sort;
	private String iconCls;
	private String description;
}
