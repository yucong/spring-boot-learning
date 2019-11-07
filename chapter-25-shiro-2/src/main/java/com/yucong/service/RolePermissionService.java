package com.yucong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.RolePermission;
import com.yucong.mapper.MenuRoleMapper;

@Service
public class RolePermissionService {

	@Autowired
	private MenuRoleMapper menuRoleMapper;

	public List<RolePermission> findMenuRoleByRoleId(Long roleId) {
		RolePermission query = new RolePermission();
		query.setRoleId(roleId);
		/// query.setFlagDel(0);
		return menuRoleMapper.select(query);
	}

}
