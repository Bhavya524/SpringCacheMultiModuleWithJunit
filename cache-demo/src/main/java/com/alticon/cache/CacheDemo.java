package com.alticon.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheDemo {
    public static void main(String[] args) {
        SpringApplication.run(CacheDemo.class, args);
        System.out.println("*********************Application Running*********************");
    }
}