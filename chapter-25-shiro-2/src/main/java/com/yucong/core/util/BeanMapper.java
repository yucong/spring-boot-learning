package com.yucong.core.util;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

public class BeanMapper {

	public static <T> T map(Object source, Class<T> clazz)  {
		if (source == null) {
			return null;
		}
		String className = clazz.getName();
		T target = null;
		try {
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>) Class.forName(className);
			target = c.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("类转换异常");
		}
		BeanUtils.copyProperties(source, target); 
		return target;
	}
	
	
	public static <T> List<T> mapList(Collection<?> sourceList,
			Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	
}
