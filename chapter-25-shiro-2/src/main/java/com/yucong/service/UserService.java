package com.yucong.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yucong.entity.User;
import com.yucong.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    /*@Autowired
    private RoleService roleService;*/

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.insertSelective(user);
        return user;
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据用户名查找用户
     */
    public User findByUsername(String username) {
    	User user = new User();
    	user.setUsername(username);
    	return userDao.selectOne(user);
    }

    /**
     * 根据用户名查找其角色
     */
    @SuppressWarnings("unchecked")
	public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        // return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
        return null;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
	public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        // return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
        return null;
    }

}