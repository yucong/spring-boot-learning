package com.yucong.core.shiro;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.yucong.core.shiro.manager.UserAuthManager;
import com.yucong.entity.User;

/**
 * 认证领域
 *
 */
@Configuration
public class ShiroAuthRealm extends AuthorizingRealm {

	@Autowired
	private UserAuthManager userAuthManager;
	
	
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
        ShiroUser shiroUser = userAuthManager.initShiroUser(user);
        return userAuthManager.initSimpleAuthenticationInfo(shiroUser, user, super.getName());
    }

    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     * 如果需要动态权限,但是又不想每次去数据库校验,可以存在ehcache中.自行完善
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    	ShiroUser shiroUser = (ShiroUser) principal.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();
        List<String> roleNames = shiroUser.getRoleNames();
        Set<String> permissions = userAuthManager.findPermissionsByRoleId(roleList);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleNames);
        info.addStringPermissions(permissions);
        return info;
    }

}