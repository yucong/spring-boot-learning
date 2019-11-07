package com.yucong.core.shiro.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yucong.core.shiro.ShiroKit;
import com.yucong.core.util.HttpContext;


/**
 * 自定义权限检查器
 */
@Service
public class PermissionCheckManager {

	public boolean checkAll() {
		HttpServletRequest request = HttpContext.getRequest();
        Long userId = ShiroKit.getUser();
        if (null == userId) {
            return false;
        }
        /*String contextPath = ConfigListener.getConf()
        		.get("contextPath");*/
        String requestURI = request.getRequestURI()/*.replaceFirst(contextPath, "")*/;
        String[] str = requestURI.split("/");
        if (str.length > 3) {
            requestURI = "/" + str[1] + "/" + str[2];
        }
        if (ShiroKit.hasPermission(requestURI)) {
            return true;
        }
        return false;
	}

	public boolean check(Object[] permissions) {
		return false;
	}

}
