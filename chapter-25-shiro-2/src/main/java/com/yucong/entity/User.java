package com.yucong.entity;

import javax.persistence.Table;

import com.yucong.core.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "sys_user")
public class User extends BaseEntity {

    private Long organizationId; //所属公司
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private Boolean locked;

}
