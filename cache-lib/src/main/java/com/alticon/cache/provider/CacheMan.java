package com.alticon.cache.provider;


import com.alticon.cache.exceptions.CachingException;

public interface CacheMan {
    String get(String key) throws Exception;
    String set(String key, String value) throws Exception;
}
