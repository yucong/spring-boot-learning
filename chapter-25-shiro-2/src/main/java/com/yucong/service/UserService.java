package com.yucong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.util.StringUtil;
import com.yucong.entity.User;
import com.yucong.entity.UserRole;
import com.yucong.mapper.UserMapper;
import com.yucong.mapper.UserRoleMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 创建用户
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.insertSelective(user);
        return user;
    }

    public User updateUser(User user) {
    	if(StringUtil.isNotEmpty( user.getPassword() ) ) {
    		passwordHelper.encryptPassword(user);
    	}
    	userDao.updateByPrimaryKeySelective(user);
    	return user;
    }

    public void deleteUser(Long userId) {
        userDao.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     */
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateByPrimaryKey(user);
    }

    public User findOne(Long userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public DataTableVO<User> findAll(int pageNum,int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
		List<User> entitys = userDao.selectAll();
		PageInfo<User> page = new PageInfo<>(entitys);
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		return new DataTableVO<User>(pageSize, allCount, allPage, currentPage, entitys);
    }

	public void updateUserRole(Long userId, List<Long> roleIds) {
		
		UserRole record = new UserRole();
		record.setUserId(userId);
		userRoleMapper.delete(record);
		
		if(!roleIds.isEmpty()) {
			List<UserRole> userRoles = new ArrayList<>();
			for(Long roleId : roleIds ) {
				UserRole userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRoles.add(userRole);
			}
			userRoleMapper.insertList(userRoles);
		}
	}

}
