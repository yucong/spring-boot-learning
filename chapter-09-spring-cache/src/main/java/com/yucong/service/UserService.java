package com.yucong.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yucong.entity.User;

import lombok.extern.slf4j.Slf4j;


/**
 * spring cache 使用示例
 * 
 * @author 喻聪
 * @date   2018-12-25
 *
 */
@Service
@Slf4j
public class UserService  {

    private static final Map<Long, User> DATABASES = new HashMap<>();

    static {
        DATABASES.put(1L, new User(1L, "u1", "p1"));
        DATABASES.put(2L, new User(2L, "u2", "p2"));
        DATABASES.put(3L, new User(3L, "u3", "p3"));
    }

    /**
     * key： 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，
     * 则缺省按照方法的所有参数进行组合（如：@Cacheable(value="user",key="#userName")）
     * 
     * value： 缓存的名称，必须指定至少一个（如：@Cacheable(value="user") 
     * 或者 @Cacheable(value={"user1","use2"})）
     */
    @Cacheable(value = "user", key = "#id")
    public User get(Long id) {
        // TODO 我们就假设它是从数据库读取出来的
        log.info("进入 get 方法");
        return DATABASES.get(id);
    }

    /**
     * 根据条件操作缓存内容并不影响数据库操作，条件表达式返回一个布尔值，true/false，
     * 当条件为true，则进行缓存操作，否则直接调用方法执行的返回结果
     * 
     * 长度： @CachePut(value = "user", key = "#user.id",condition = "#user.username.length() < 10") 
     * 只缓存用户名长度少于10的数据
     * 
     * 大小： @Cacheable(value = "user", key = "#id",condition = "#id < 10") 
     * 只缓存ID小于10的数据
     * 
     * 组合： @Cacheable(value="user",key="#user.username.concat(##user.password)")
     * 
     */
    @CachePut(value = "user", key = "#user.id", condition = "#user.username.length() < 10")
    public User saveOrUpdate(User user) {
        DATABASES.put(user.getId(), user);
        log.info("进入 saveOrUpdate 方法");
        return user;
    }
    
    /**
     * 
     * 提前操作： @CacheEvict(value="user",allEntries=true,beforeInvocation=true) 加上beforeInvocation=true后，
     * 不管内部是否报错，缓存都将被清除，默认情况为false
     * 
     * allEntries： 是否清空所有缓存内容，缺省为 false，
     * 如果指定为 true，则方法调用后将立即清空所有缓存（如：@CacheEvict(value = "user", key = "#id", allEntries = true)）
     * 
     * beforeInvocation： 是否在方法执行前就清空，缺省为 false，
     * 如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，
     * 如果方法执行抛出异常，则不会清空缓存（如：@CacheEvict(value = "user", key = "#id", beforeInvocation = true)）
     * 
     */
    @CacheEvict(value = "user", key = "#id", allEntries = true, beforeInvocation = true)
    public void delete(Long id) {
        DATABASES.remove(id);
        log.info("进入 delete 方法");
    }
}
