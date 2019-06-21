package com.yucong.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.java.common.log.model.ServerExceptionLog;
import com.java.util.http.HttpIpAddress;
import com.java.util.http.ResponseUtil;
import com.yucong.constants.ConfigConsts;
import com.yucong.enums.CodeEnum;
import com.yucong.producer.LogProducer;
import com.yucong.vo.common.ExceptionVO;

import lombok.extern.slf4j.Slf4j;


/**
 * 异常处理大管家
 * 
 * @author 喻聪
 * @date   2017-10-09
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final String MESSAGE_SERVER_EXCEPTION = "服务器内部错误";
	
	@Autowired
	private LogProducer logProducer;
	
	/**
	 * 处理运行时异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public void handleException(HttpServletRequest request,HttpServletResponse response,RuntimeException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.SERVER_ERROR.getCode());
		returnVO.setErrorCode(500);
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		returnVO.setErrorMsg(e.getMessage());
		log.error("服务器内部异常",e);
		recordLogInMongo(request,response,e);
		ResponseUtil.write(response, returnVO);
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
	public void handleException(HttpServletRequest request,HttpServletResponse response,Exception e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.SERVER_ERROR.getCode());
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		log.error("服务器内部异常",e);
		returnVO.setErrorCode(501);
		returnVO.setErrorMsg(e.getMessage());
		recordLogInMongo(request,response,e);
		ResponseUtil.write(response, returnVO);
	}
	
	private void recordLogInMongo(HttpServletRequest request,HttpServletResponse response,Exception e) {
		ServerExceptionLog log = new ServerExceptionLog();
		log.setDeviceType(request.getHeader(ConfigConsts.DEVICE_TYPE_KEY));
		log.setClientIP(HttpIpAddress.getIpAddr(request));
		log.setRequestUrl(request.getRequestURI());
		log.setMethod(request.getMethod());
		log.setStackTrace(getStackTrace(e));
		log.setErrMsg(e.getMessage());		
		log.setServerIP(request.getLocalAddr());
		log.setUserId(request.getHeader(ConfigConsts.X_USER_ID_KEY));
		logProducer.produceExceptionLog(log);
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
