package com.yucong.core.aop;

import java.lang.reflect.Method;

import javax.naming.NoPermissionException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yucong.core.annotion.Auth;
import com.yucong.core.shiro.manager.PermissionCheckManager;

/**
 * 权限检查的aop
 *
 * @author fengshuonan
 * @date 2017-07-13 21:05
 */
@Aspect
@Component
@Order(200)
public class PermissionAop {

    @Autowired
    private PermissionCheckManager permissionCheckService;

    @Pointcut(value = "@annotation(com.yucong.core.annotion.Auth)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable,NoPermissionException {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Auth auth = method.getAnnotation(Auth.class);
        Object[] permissions = auth.value();
        if (permissions.length == 0) {

            //检查全体角色
            boolean result = permissionCheckService.checkAll();
            if (result) {
                return point.proceed();
            } else {
            	System.out.println("没有访问权限...");
                throw new NoPermissionException("没有访问权限");
            }

        } else {

            //检查指定角色
            boolean result = permissionCheckService.check(permissions);
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        }
    }

}
