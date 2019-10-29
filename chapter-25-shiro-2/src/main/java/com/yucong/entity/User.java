package com.yucong.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //编号
    private Long organizationId; //所属公司
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private Boolean locked = Boolean.FALSE;

}
