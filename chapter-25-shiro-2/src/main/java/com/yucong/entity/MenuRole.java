package com.yucong.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_menu_role")
public class MenuRole extends BaseEntity {
    
    private Long menuId;
    private Long roleId;

}