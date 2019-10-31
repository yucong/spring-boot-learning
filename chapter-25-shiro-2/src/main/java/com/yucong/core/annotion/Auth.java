package com.yucong.core.annotion;

import java.lang.annotation.*;

/**
 * 权限注解 用于检查权限 规定访问权限
 *
 * @example @Auth({role1,role2})
 * @example @Auth
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Auth {

    /**
     * 角色英文名称
     * 使用注解时加上这个值表示限制只有某个角色的才可以访问对应的资源
     * 常用在某些资源限制只有超级管理员角色才可访问
     */
    String[] value() default {};
}
