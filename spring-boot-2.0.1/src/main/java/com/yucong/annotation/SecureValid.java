package com.yucong.annotation;

import java.lang.annotation.*;

/** 
 * 在类或方法上添加@SecureValid就验证接口签名
 * 
 * @author 喻聪
 * @date   2018-01-25 
 *  
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureValid {

    
}
