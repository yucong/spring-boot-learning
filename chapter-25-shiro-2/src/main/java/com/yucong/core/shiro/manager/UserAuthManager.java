/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yucong.core.shiro.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.core.shiro.ShiroKit;
import com.yucong.core.shiro.ShiroUser;
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
	
	public ShiroUser initShiroUser(User user) {
		ShiroUser shiroUser = ShiroKit.createShiroUser(user);

        //用户角色数组
		List<Role> roles = roleMapper.findByUserId(user.getId());

        //获取用户角色列表
        List<Long> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role.getId());
            roleNameList.add(role.getRole());
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
	}
	
	public SimpleAuthenticationInfo initSimpleAuthenticationInfo(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

	public Set<String> findPermissionsByRoleId(List<Long> roleList) {
		return permissionMapper.findPermissionByRoleIds(roleList);
	}

}
