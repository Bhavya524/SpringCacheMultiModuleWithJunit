package com.alticon.cache.provider.redis;

import com.alticon.cache.exceptions.CachingException;
import com.alticon.cache.provider.CacheMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class RedisCacheMan implements CacheMan {

    public RedisCacheMan(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String get(String key) throws Exception {
        String value;
        try {
            value = Objects.requireNonNull(redisTemplate.opsForValue().get(key));
        } catch (Exception ex) {
            if (
                    ex instanceof RedisConnectionFailureException || ex instanceof RedisSystemException
            ) {
                throw new CachingException(ex);

            } else {
                throw new Exception();
            }
        }
        return value;
    }

    @Override
    public String set(String key, String value) throws Exception {
        String cache;
        try {
            redisTemplate.opsForValue().set(key, value);
            cache = key + ", " + value;
        } catch (Exception ex) {
            if (
                    ex instanceof RedisConnectionFailureException || ex instanceof RedisSystemException
            ) {
                throw new CachingException(ex);

            } else {
                throw new Exception();
            }
        }
            return cache;
    }
}
