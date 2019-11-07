package com.yucong.entity;

import javax.persistence.Table;

import com.yucong.core.base.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Permission extends BaseEntity {
	
    private String name; //资源名称
    private String type;; //资源类型
    // private String url; //资源路径
    private String permission; //权限字符串
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available;
    private Integer sort; // 排序



}
