package com.yucong.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 *
 */
public class ShiroKit {

	/**
     * 通过用户表的信息创建一个shiroUser对象
     */
    /*public static ShiroUser createShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        if (user == null) {
            return shiroUser;
        }
        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getUsername());
        // shiroUser.setDeptId(user.getDeptId());
        // shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));
        shiroUser.setName(user.getUsername());
        // shiroUser.setEmail(user.getEmail());
        // shiroUser.setAvatar(user.getAvatar());
        return shiroUser;
    }*/
	
	/**
     * 获取封装的 ShiroUser
     */
    public static String getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (String) getSubject().getPrincipals().getPrimaryPrincipal();
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
    
    
    
    /** 
     * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限) 
     * https://blog.csdn.net/jizhunboss/article/details/53606808
     * 
     * @param myRealm 自定义的realm 
     * @param username 用户名 
     */  
    public static void reloadAuthorizing(String username){  
        
    	
    	
    	RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
    	ShiroAuthRealm shiroRealm = (ShiroAuthRealm)rsm.getRealms().iterator().next();
		
    	Subject subject = SecurityUtils.getSubject(); 
		String realmName = subject.getPrincipals().getRealmNames().iterator().next();
		
    	//shiroRealm.clearAllCachedAuthorizationInfo();
		
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName);
    	shiroRealm.clearCachedAuthorizationInfo(principals);
    	
		//shiroRealm.clearAllCachedAuthenticationInfo(principals);
		
		// logger.info("oper.user="+user.getEmail()+",login.user="+SecurityUtils.getSubject().getPrincipal().toString());
		
		//shiroRealm.clearAllCachedAuthorizationInfo2();//清楚所有用户权限
		
		
		//第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户 
		/*SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName); 
		subject.runAs(principals); 
		shiroRealm.getAuthorizationCache().remove(subject.getPrincipals()); 
		subject.releaseRunAs();*/

    	
		
    	
    	
    	/*Subject subject = SecurityUtils.getSubject();   
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();   
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户   
        SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName);   
        subject.runAs(principals);   
        myRealm.getAuthorizationCache().remove(subject.getPrincipals());   
        subject.releaseRunAs();  */
    }


    
}
