package com.yucong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucong.core.base.vo.DataTableVO;
import com.yucong.core.shiro.ShiroKit;
import com.yucong.core.util.BeanMapper;
import com.yucong.core.util.StringUtil;
import com.yucong.entity.Role;
import com.yucong.entity.User;
import com.yucong.entity.UserRole;
import com.yucong.mapper.RoleMapper;
import com.yucong.mapper.UserMapper;
import com.yucong.mapper.UserRoleMapper;
import com.yucong.vo.user.ListUserVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

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

    public void lockedUser(Long userId) {
    	User user = userDao.selectByPrimaryKey(userId);
    	user.setLocked(!user.getLocked());
        userDao.updateByPrimaryKeySelective(user);
        
        // 冻结用户，需要清除缓存
        ShiroKit.reloadAuthorizing(userId);
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

    public User findById(Long userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public DataTableVO<ListUserVO> findAll(String username,int pageNum,int pageSize) {
    	
    	Example example = new Example(User.class);
    	Criteria criteria = example.createCriteria();
    	if(StringUtil.isNotEmpty(username)) {
    		criteria.andLike("username", "%" + username + "%");
    	}
    	PageHelper.startPage(pageNum, pageSize);
		List<User> entitys = userDao.selectByExample(example);
		PageInfo<User> page = new PageInfo<>(entitys);
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		
		List<ListUserVO> userVOs = BeanMapper.mapList(entitys, ListUserVO.class);
		for(ListUserVO user : userVOs) {
			List<Role> roles = roleMapper.findByUserId(user.getId());
			user.setRoles(roles);
		}
		return new DataTableVO<ListUserVO>(pageSize, allCount, allPage, currentPage, userVOs);
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
		
		// 更新用户角色，需要清除缓存
		ShiroKit.reloadAuthorizing(userId);
		
	}

}
