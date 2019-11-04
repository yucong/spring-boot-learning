package com.yucong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.MenuRole;
import com.yucong.mapper.MenuRoleMapper;

@Service
public class MenuRoleService {

	@Autowired
	private MenuRoleMapper menuRoleMapper;

	public List<MenuRole> findMenuRoleByRoleId(Long roleId) {
		MenuRole query = new MenuRole();
		query.setRoleId(roleId);
		/// query.setFlagDel(0);
		return menuRoleMapper.select(query);
	}

}
