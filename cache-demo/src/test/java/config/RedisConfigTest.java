//package config;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.test.context.TestConfiguration;
//import redis.embedded.RedisServer;
//
//import java.io.IOException;
//
//@TestConfiguration
//public class RedisConfigTest {
//    private RedisServer redisServer;
//
//    public RedisConfigTest(RedisProperties redisProperties) throws IOException {
//        this.redisServer = new RedisServer(redisProperties.getPort());
//    }
//
//    @PostConstruct
//    public void postConstruct() {
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void preDestroy() {
//        redisServer.stop();
//    }
//
//}