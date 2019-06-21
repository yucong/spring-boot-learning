package com.yucong.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.java.common.log.constant.LogConstants;
import com.java.common.log.model.HttpRequestLog;
import com.java.util.http.HttpHelper;
import com.java.util.http.HttpIpAddress;
import com.java.util.json.FastJsonUtil;
import com.yucong.constants.ConfigConsts;
import com.yucong.constants.Env;
import com.yucong.producer.LogProducer;

import lombok.extern.slf4j.Slf4j;


/**
 * @author 喻聪
 *
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "bodyFilter")
@Order(2)
public class HttpServletRequestReplacedFilter implements Filter {

	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("druid","webjars","swagger","api-docs")));
	
	@Autowired
	private LogProducer producer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("HttpServletRequestReplacedFilter初始化了");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//如果是OPTIONS请求，不需要抓包
		BodyReaderHttpServletRequestWrapper	requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
		String method = requestWrapper.getMethod();
		if(method.equals("OPTIONS")) {
	    	return ;
	    }
		
		String requestURI = requestWrapper.getRequestURI();
		log.info("查看请求的URL：" + requestURI);
        
		if (isIgore(requestURI)) {
			
            chain.doFilter(request, response);
        
        } else {
        	
			long startTime = System.currentTimeMillis();
			String threadName = Thread.currentThread().getName();
			log.info("startTime:" + startTime + ",trheadName:" + threadName);
			
			// 包装响应对象 resp 并缓存响应数据
			ResponseWrapper mResp = new ResponseWrapper((HttpServletResponse) response); 
			chain.doFilter(requestWrapper, mResp);
			
			String param = null;
			if (method.equalsIgnoreCase("GET")) {
				param = requestWrapper.getQueryString();
			} else {
				param = HttpHelper.getBodyData(requestWrapper.getReader());
				if (Base64.isBase64(param)) {
					param = new String(Base64.decodeBase64(param), StandardCharsets.UTF_8);
				}
			}
			
			//获取Http请求的出参
			log.info("查看入参:" + param);
			byte[] bytes = mResp.getBytes(); // 获取缓存的响应数据
			log.info("查看出参：" + new String(bytes, ConfigConsts.DEFAUT_ENCODE));
	
			//从header中得到token
			String deviceType = requestWrapper.getHeader(ConfigConsts.DEVICE_TYPE_KEY);
			String userId = requestWrapper.getHeader(ConfigConsts.X_USER_ID_KEY);
			String appVersion = requestWrapper.getHeader(ConfigConsts.APP_VERSION);
			HttpRequestLog httpLog = new HttpRequestLog();
			httpLog.setUserId(userId);
			httpLog.setDeviceType(deviceType == null ? LogConstants.DeviceType.UNKONW : deviceType);
			httpLog.setAppVersion(appVersion);
			httpLog.setMethod(method);
			httpLog.setRequestParam(param);
			String respData = null;
			if(bytes.length == 0) {
				respData = "{\"status\":404}";
			} else {
				respData = new String(bytes, ConfigConsts.DEFAUT_ENCODE);
			}
			httpLog.setResponseData(FastJsonUtil.parseJsonObject(respData, Object.class));
			//获取客户端真实IP
			String clientIP = HttpIpAddress.getIpAddr(requestWrapper);
			httpLog.setClientIP(clientIP);
			httpLog.setRequestUrl(requestWrapper.getRequestURI());
			httpLog.setServerIP(requestWrapper.getLocalAddr());
			long endTime = System.currentTimeMillis();
			long consumeTime = endTime - startTime;
			httpLog.setConsumeTime(consumeTime);
			log.info("endTime:" + startTime + ",trheadName:" + threadName);
			if(Env.isSendMQ) {
				producer.produceRequestLog(httpLog);
			}
			response.getOutputStream().write(bytes);
        }
	}

	@Override
	public void destroy() {

	}
	
	private boolean isIgore(String url) {
		for(String path : ALLOWED_PATHS) {
			if(url.contains(path) ) {
				return true;
			}
		}
		return false;
	}


}
