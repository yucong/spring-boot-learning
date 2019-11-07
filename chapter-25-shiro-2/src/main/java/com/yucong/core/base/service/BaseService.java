package com.yucong.core.base.service;

import java.util.List;

import com.yucong.BaseMapper;
import com.yucong.core.base.entity.BaseEntity;
import com.yucong.exception.BusinessException;
import com.yucong.exception.DaoException;


public abstract class BaseService<Entity extends BaseEntity,Mapper extends BaseMapper<Entity>> {

	public abstract Mapper getMapper();
	
	/**
	 * 
	 * 添加
	 * 
	 * @author 喻聪
	 * @date   2018-01-25
	 */
	public int add(Entity entity,Long userId) {
		/// entity.setCreateUser(userId);
		int result = getMapper().insertSelective(entity);
		if(result == 0) {
			throw new DaoException(100, "数据执行添加操作失败");
		}
		return result;
	}
	
	/**
	 * 更新
	 * 
	 * @author 喻聪
	 * @date   2018-01-25 
	 */
	public int update(Entity entity,Long userId) {
		/// entity.setUpdateTime(new Date());
		/// entity.setUpdateUser(userId);
		int result = getMapper().updateByPrimaryKeySelective(entity);
		if(result == 0) {
			throw new DaoException(101, "数据执行更新操作失败");
		}
		return result;
	}
	
	/**
	 * 查询所有
	 *   
	 * @author 喻聪  
	 *   
	 */
	public List<Entity> list() {
		List<Entity> entitys = getMapper().selectAll();
		return entitys;
	}
	
	public Entity detail(int id) {
		
		Entity entity = getMapper().selectByPrimaryKey(id);
		if(entity == null) {
			throw new BusinessException(400,"查询id不存在");
		}
		return entity;
	}
	
}
