package com.yucong.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yucong.core.base.ExceptionVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理大管家
 * 
 * @author 喻聪
 * @date   2017-10-09
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final String MESSAGE_SERVER_EXCEPTION = "服务器内部错误";
	// private static final String MESSAGE_PARAMTER_ILLEGAL  = "请求参数不合法";
	private static final String MESSAGE_METHOD_ILLEGAL  = "请求方式不支持";
	
	
	/**
	 * 不支持的请求方式
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ExceptionVO handleException(HttpServletRequest request, HttpServletResponse response,HttpRequestMethodNotSupportedException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(400);
		returnVO.setMessage(MESSAGE_METHOD_ILLEGAL);
		returnVO.setErrorCode(400);
		returnVO.setErrorMsg(e.getMessage());
		log.warn("不支持的请求方式",e);
		return returnVO;
	}
	
	
	/**
	 * 处理入参异常：只返回第一个不合法的参数错误
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = NoPermissionException.class)
	public ExceptionVO handleException(HttpServletRequest request,HttpServletResponse response,NoPermissionException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(400);
		returnVO.setMessage("权限不够");
		returnVO.setErrorCode(400);
		returnVO.setErrorMsg(e.getMessage());
		return returnVO;
	}
	
	
	
	
	/**
	 * 处理运行时异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public ExceptionVO handleException(HttpServletRequest request,HttpServletResponse response,RuntimeException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(501);
		returnVO.setErrorCode(501);
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		returnVO.setErrorMsg(e.getMessage());
		log.error("服务器内部异常",e);
		return returnVO;
	}
	
	/**
	 * 处理运行时异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = Exception.class)
	public ExceptionVO handleException(HttpServletRequest request,HttpServletResponse response,Exception e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(500);
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		log.error("服务器内部异常",e);
		returnVO.setErrorCode(500);
		returnVO.setErrorMsg(e.getMessage());
		return returnVO;
	}
	
	
	
	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
	
	
}
