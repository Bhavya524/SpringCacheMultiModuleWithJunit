package config.provider;

import com.alticon.cache.model.CacheDemoDTO;
import com.alticon.cache.provider.redis.RedisCacheMan;
import config.RedisConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

import static org.mockito.Mockito.when;


@SpringBootTest(classes = RedisConfiguration.class)
public class RedisCacheManTest {
    @InjectMocks
    private RedisCacheMan redisCacheMan;
    @Autowired
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    RedisServer redisServer;

    @BeforeEach
    void setUp() {
        redisServer = RedisServer.builder().build();
        redisServer.start();
    }

    @AfterEach
    void tearDown() {
        redisServer.stop();
    }

    @Test
    public void shouldSetUser_toRedis() throws Exception {
        CacheDemoDTO cacheDemoDTO = new CacheDemoDTO("gtr", "erf");
        redisTemplate.opsForValue().set(cacheDemoDTO.getKey(), cacheDemoDTO.getValue());
        String value = redisTemplate.opsForValue().get("gtr");
        when(value).thenReturn("erf");
        String saved = redisCacheMan.set(cacheDemoDTO.getKey(), cacheDemoDTO.getValue());
        Assertions.assertEquals(cacheDemoDTO.getKey(), cacheDemoDTO.getValue(), saved);

    }
}
