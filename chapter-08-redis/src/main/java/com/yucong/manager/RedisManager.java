package com.yucong.manager;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * spring-data-redis 管理类
 * 
 * @author 喻聪
 * @date   2018-01-25
 */
@Component
public class RedisManager {

	@Autowired
	public RedisTemplate<String, String> redisTemplate;


	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * 
	 * @param key	缓存的键值
	 * @param value	缓存的值
	 * @return 		缓存的对象
	 */
	public void setObject(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 获得缓存的基本对象。
	 * 
	 * @param key	缓存键值
	 * @return 		缓存键值对应的数据
	 */
	public String getObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 删除缓存的对象
	 * 
	 * @param key	缓存键值
	 */
	public void delete(String key) {
		 redisTemplate.delete(key);
	}




	

	/**
	 * 算是删除吧，只保留start与end之间的记录
	 * 
	 * @param key
	 * @param start 记录的开始位置(0表示第一条记录)
	 * @param end 	记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 		执行状态码
	 * */
	public void trim(String key, int start, int end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 删除List中c条记录，被删除的记录值为value
	 * 
	 * @param key	键
	 * @param i 	要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param obj 	要匹配的值
	 * @return 		删除后的List中的记录数
	 * */
	public long remove(String key, long i, Object obj) {
		return redisTemplate.opsForList().remove(key, i, obj);
	}



	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param time
	 * @param unit
	 * @return
	 */
	public boolean expire(String key, long time, TimeUnit unit) {
		return redisTemplate.expire(key, time, unit);
	}

	



	

	


}