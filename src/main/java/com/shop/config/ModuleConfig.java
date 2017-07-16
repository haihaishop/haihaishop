package com.shop.config;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by 15764 on 2017-07-16.
 */

@Configuration
@EnableTransactionManagement()
//启动缓存
@EnableCaching()
//@EnableJms()
@ComponentScan(basePackages = { "com.shop.service" })
public class ModuleConfig {
    // 引入配置文件
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(
            @Value("classpath:db.properties") Resource locationDB,
            @Value("classpath:redis.properties") Resource  locationsRedis,
            @Value("classpath:mq.properties") Resource  locationsMq) {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(locationDB,locationsRedis,locationsMq);
        return propertyPlaceholderConfigurer;
    }


    @Bean
    public DataSource dataSource(@Value("${datasource.userName}") String userName,
                                 @Value("${datasource.password}") String password,
                                 @Value("${datasource.driverClassName}") String driverClassName,
                                 @Value("${datasource.jdbcUrl}") String jdbcUrl) {
        HikariDataSource dataSource=new HikariDataSource();
        //BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        return dataSource;
    }

    // 接口所在包名，Spring会自动查找其下的类
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.shop.mapper");
        return mapperScannerConfigurer;
    }

    // (事务管理)transaction manager, use JtaTransactionManager for global tx
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    //pagehelper的资源参数文件
    @Bean
    public PropertiesFactoryBean maperHelper(@Value("classpath:helpconfig.properties")Resource location){
        PropertiesFactoryBean maperHelper=new PropertiesFactoryBean();
        maperHelper.setLocation(location);
        return maperHelper;
    }
    // spring和MyBatis完美整合，不需要mybatis的配置映射文件
    // 集成pagehelper
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, @Value("#{maperHelper}") Properties properties) throws Exception {
        //拦截器的方式使用pagehelper
        Interceptor interceptor=new PageInterceptor();
        interceptor.setProperties(properties);
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setPlugins(new Interceptor[]{interceptor});
        return sqlSessionFactory.getObject();
    }
    //redis配置参考http://www.cnblogs.com/s648667069/p/6473412.html
    //jedis连接池 配置
    @Bean
    public JedisPoolConfig redisPoolConfig(@Value("${redis.maxIdle}")String maxIdle,
                                           @Value("${redis.maxActive}")String maxActive,
                                           @Value("${redis.maxWait}")String maxWait,
                                           @Value("${redis.testOnBorrow}")String testOnBorrow){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Integer.parseInt(maxIdle));
//		poolConfig.setMaxActive(maxActive);
//		poolConfig.setMaxWait(maxWait);
        poolConfig.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
        return poolConfig;
    }

    // redis服务器中心
    @Bean
    @Autowired
    public JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host}")String host,
                                                         @Value("${redis.port}")int port,
                                                         @Value("${redis.pass}")String password,
                                                         JedisPoolConfig redisPoolConfig){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);
        connectionFactory.setPoolConfig(redisPoolConfig);
        return connectionFactory;
    }

    // redis template
    @Bean
    public RedisTemplate redisTemplate( JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        StringRedisSerializer keySerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer=new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    // cache manager
    @Bean
    public RedisCacheManager cacheManager(@Value("#{redisTemplate}") RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        return redisCacheManager;
    }

}

