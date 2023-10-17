package com.alticon.cache.config;

import com.alticon.cache.provider.CacheMan;
import com.alticon.cache.provider.redis.RedisCacheMan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@ConditionalOnProperty(prefix = "com.alticon.cache", name = "mode", havingValue = "redis")
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String hostname;
    @Value("${spring.data.redis.port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(hostname);
        standaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(standaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
        return template;
    }
}
