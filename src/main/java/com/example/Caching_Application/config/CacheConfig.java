package com.example.Caching_Application.config;

import com.example.Caching_Application.dto.EmployeeDto;
import com.example.Caching_Application.entities.Employee;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

@Configuration
@EnableCaching  // without this annotation caching will remain disabled and we can use caching related annotation
public class CacheConfig {

//    configure RedisCacheMananger
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {


        RedisCacheConfiguration redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("my-redis-")      // add prefix to all cache names in redis cache
                .entryTtl(Duration.ofSeconds(60))       // data Time to live in redis cache is 1 min
                .enableTimeToIdle()  //this line reset the expiration time or TTL if it get accessed before TTL expires
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JacksonJsonRedisSerializer<EmployeeDto>(EmployeeDto.class)))
                ;

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

}
