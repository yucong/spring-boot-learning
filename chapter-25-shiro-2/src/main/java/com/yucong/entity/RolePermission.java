package com.yucong.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "sys_role_permission")
public class RolePermission extends BaseEntity {
	
    private Long roleId; 
    private Long permissionId; 

}
