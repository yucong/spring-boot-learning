package com.yucong.core.shiro.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.Role;
import com.yucong.entity.User;
import com.yucong.mapper.PermissionMapper;
import com.yucong.mapper.RoleMapper;
import com.yucong.mapper.UserMapper;

@Service
public class UserAuthManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    

	public User findByUsername(String username) {
		
		User record = new User();
		record.setUsername(username);
		User user = userMapper.selectOne(record);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getLocked()) {
            throw new LockedAccountException();
        }
        return user;
	}
	
	public List<Role> findRolesByUserId(Long userId) {
		List<Role> roles = roleMapper.findByUserId(userId);
		return roles;
	}

	public Set<String> findPermissionsByRoleId(List<Long> roleList) {
		if(roleList.isEmpty()) {
			return new HashSet<>(0);
		}
		return permissionMapper.findPermissionByRoleIds(roleList);
	}
	
	
	
	
	

}
