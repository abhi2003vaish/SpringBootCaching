package com.example.Caching_Application.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching  // without this annotation caching will remain disabled and we can use caching related annotation
public class CacheConfig {

}
