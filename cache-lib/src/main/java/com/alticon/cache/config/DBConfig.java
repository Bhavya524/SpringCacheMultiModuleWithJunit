package com.alticon.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "com.alticon.cache", name = "mode", havingValue = "database")
public class DBConfig {

}
