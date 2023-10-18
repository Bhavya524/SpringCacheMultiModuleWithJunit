package test2;

import com.alticon.cache.provider.redis.RedisCacheMan;
import config.RedisConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.embedded.RedisServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = RedisConfiguration.class)
public class RedisTemplateTest {
    @InjectMocks
    private RedisCacheMan redisCacheMan;
    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    @InjectMocks
    private static RedisServer redisServer;
    @MockBean
    private ValueOperations<String, String> valueOperations;

        @Test
    public void testMockRedisTemplate() {
        RedisTemplate<String, String> mockRedisTemplate = Mockito.mock(RedisTemplate.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn((ValueOperations<String, String>) mockRedisTemplate);
    }
    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        redisServer = RedisServer.builder().build();
        redisServer.start();
    }

    @AfterEach
    void tearDown() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }


    @Test
    public void testGetValue() throws Exception {
        String key = "testKey";
        String value = "testValue";

        redisTemplate.opsForValue().set(key, value);

        when(redisTemplate.opsForValue().get(key)).thenReturn(value);

        String retrievedValue = redisCacheMan.get(key);
        Assertions.assertEquals(value, retrievedValue);


    }
}
