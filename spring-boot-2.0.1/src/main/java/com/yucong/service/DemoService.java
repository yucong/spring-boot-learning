package com.yucong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.util.dozer.BeanMapper;
import com.yucong.entity.User;
import com.yucong.mapper.UserMapper;
import com.yucong.vo.UserVO;
import com.yucong.vo.common.DataTableVO;

@Service
public class DemoService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 分页示例代码
	 * 
	 * @author 喻聪
	 * @date   2019-06-21
	 */
	public DataTableVO<UserVO> list(String username,int page,int size) {
		
		User query = new User();
		query.setUsername(username);
		
		PageHelper.startPage(page, size);
		List<User> list = userMapper.select(query);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		int pageSize  = pageInfo.getPageSize();
		long allCount = pageInfo.getTotal();
		int allPage = pageInfo.getPages();
		int currentPage = pageInfo.getPageNum();
		List<UserVO> userVOs = BeanMapper.mapList(list, UserVO.class);
		return new DataTableVO<UserVO>(pageSize, allCount, allPage, currentPage,userVOs);
	}
	
	
}
