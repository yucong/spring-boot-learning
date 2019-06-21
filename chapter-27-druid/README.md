

相关文档
https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98


31. 如何在Spring Boot中集成Druid连接池和监控？
https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter


31. 如何在Spring Boot中添加自定义WallConfig、Filter ？
https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter#如何配置-filter


32. 如何在 Spring Boot 中配置数据库密码加密？
先看常见问题#21，如何生成加密后的密码及秘钥：我希望加密我的数据库密码怎么办？
进行配置
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
# 生成的加密后的密码（原密码 123456）
spring.datasource.password=WVMjPhfXQrIsWRo0/RCqAVvYtTU9WNVToKJohb8AlUmHwnV6vwFL+FM2CNFDMJwGHW1iCmyaUlF+sgvFdogqEA==
# 生成的公钥
public-key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIiwHpFrDijV+GzwRTzWJk8D3j3jFfhsMFJ/7k1NTvBuLgL+TdIHgaMNOIEjHpXzuvX38J3FtOK8hLrySncVGOMCAwEAAQ==
# 配置 connection-properties，启用加密，配置公钥。
spring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${public-key}
# 启用ConfigFilter
spring.datasource.druid.filter.config.enabled=true


本文参考博客：
Spring boot整合mysql和druid
https://blog.csdn.net/sinat_32366329/article/details/84404944

github源代码
https://github.com/superRabbitMan/spring-boot-mysql/blob/master/pom.xml

 
 
