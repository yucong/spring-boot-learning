package com.yucong.interceptor;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.yucong.exception.ParameterIllegalException;


/**
 * 检查入参合法性
 * 
 * @author 喻聪
 * @date   2018-01-26
 * 
 */
@Aspect
@Component
@Order(1)
public class ValidParamAspect {

	// Controller层切点
	@Pointcut("execution (* com.yucong.controller..*.*(..))")
	public void aspect() { }

	@Before("aspect()")
    public void doBefore(JoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		if(args != null && args.length > 1 ) {
			//取出第2个参数
			Object obj = jp.getArgs()[1];
			if(obj instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult)obj;
				// 校验返回错误集合中的第一个错误信息
				if (bindingResult.hasErrors()) {
					List<ObjectError> errors = bindingResult.getAllErrors();
					throw new ParameterIllegalException(errors.get(0).getDefaultMessage());
				} 
			}
		} 
    }

}