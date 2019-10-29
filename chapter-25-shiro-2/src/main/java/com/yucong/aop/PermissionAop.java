package com.yucong.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yucong.annotion.Auth;
import com.yucong.service.PermissionCheckService;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

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
    private PermissionCheckService permissionCheckService;

    @Pointcut(value = "@annotation(com.yucong.annotion.Auth)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
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
                throw new NoPermissionException();
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
