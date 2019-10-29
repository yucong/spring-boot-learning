package com.yucong.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yucong.entity.User;

/**
 * shiro工具类
 *
 */
public class ShiroKit {

	/**
     * 通过用户表的信息创建一个shiroUser对象
     */
    public static ShiroUser createShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        if (user == null) {
            return shiroUser;
        }
        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getUsername());
        // shiroUser.setDeptId(user.getDeptId());
        // shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));
        // shiroUser.setName(user.getName());
        // shiroUser.setEmail(user.getEmail());
        // shiroUser.setAvatar(user.getAvatar());
        return shiroUser;
    }
	
	/**
     * 获取封装的 ShiroUser
     */
    public static ShiroUser getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }
    
    
    /**
     * 认证通过或已记住的用户。与guset搭配使用。
     */
    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
     */
    public static boolean isGuest() {
        return !isUser();
    }
    
    /**
     * 获取当前 Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean hasPermission(String permission) {
        return getSubject() != null && permission != null
                && permission.length() > 0
                && getSubject().isPermitted(permission);
    }


    
}
