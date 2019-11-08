package com.yucong.core.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.yucong.core.shiro.manager.UserAuthManager;
import com.yucong.entity.Role;
import com.yucong.entity.User;

/**
 * 认证领域
 *
 */
//@Configuration
public class ShiroAuthRealm extends AuthorizingRealm {

	@Autowired
	private UserAuthManager userAuthManager;
	
	@Autowired
	private SessionDAO sessionDAO;
	
	
    /**
     * 认证回调函数,登录时调用
     * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
     * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
     * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
     * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
     * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），
     * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        
    	String principal = (String) token.getPrincipal();
        User user = userAuthManager.findByUsername(principal);
        String credentials = user.getPassword();

        // 密码加盐处理
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user.getId(), credentials, credentialsSalt, super.getName());
    }

    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     * 如果需要动态权限,但是又不想每次去数据库校验,可以存在ehcache中.自行完善
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    	
    	Long userId = (Long) principal.getPrimaryPrincipal();
        
    	//获取用户角色列表
    	List<Role> roles = userAuthManager.findRolesByUserId(userId);
        List<Long> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role.getId());
            roleNameList.add(role.getRole());
        }
        Set<String> permissions = userAuthManager.findPermissionsByRoleId(roleList);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleNameList);
        info.addStringPermissions(permissions);
        return info;
    }
    
    /**
     * 清除指定用户权限缓存
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    
    /**
     * 清除所有用户的权限缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }
    
    /**
     * 清除指定用户的session
     */
    public void clearCachedSession(Long userId) {
    	Collection<Session> sessions = sessionDAO.getActiveSessions();
    	for(Session session:sessions) {
    		Object sessionKey = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
    		if(sessionKey == null) {
    			continue;
    		}
    		Long sessionKeyVaue = Long.valueOf(sessionKey.toString());
    		if(userId.equals(sessionKeyVaue) ) {
    			sessionDAO.delete(session);
    		}
    	}
    	
    }

    
    /**
     * 清除指定用户的session
     */
    public void test() {
    	
    	Collection<Session> sessions = sessionDAO.getActiveSessions();
    	for(Session session:sessions) {
    		
    		System.out.println("登录ip:" + session.getHost());
    		System.out.println("sessionId:" + session.getId());
	    	System.out.println("登录用户:" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
	    	System.out.println();
	    	
	    	for(Object o : session.getAttributeKeys()) {
	    		System.out.println("key:" + o.toString() + ",v:" + session.getAttribute(o));
	    	}
	    	System.out.println("最后操作日期:" + session.getLastAccessTime());

    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 清除身份认证
     */
    /*@Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }*/

    /**
     * 清除所有用户的身份认证
     */
    /*public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }*/

    /**
     * 清除所有用户的身份认证和权限认证
     */
    /*public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }*/
    
    /**
     * 清除用户的身份认证和权限认证
     */
    /*@Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }*/

}