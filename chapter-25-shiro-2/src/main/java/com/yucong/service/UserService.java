package com.yucong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.util.StringUtil;
import com.yucong.entity.User;
import com.yucong.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private PasswordHelper passwordHelper;

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
    	userDao.updateByPrimaryKey(user);
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

    public DataTableVO<User> findAll(int pageSize,int size) {
    	PageHelper.startPage(pageSize, size);
		List<User> entitys = userDao.selectAll();
		PageInfo<User> page = new PageInfo<>(entitys);
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		return new DataTableVO<User>(pageSize, allCount, allPage, currentPage, entitys);
    }

}
