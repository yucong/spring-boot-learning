package com.yucong.core.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*private Integer state;
	private Integer flagDel;
	private Integer createUser;
	private Date    createTime;
	private Integer updateUser;
	private Date    updateTime;
	private String  memo;*/
}
