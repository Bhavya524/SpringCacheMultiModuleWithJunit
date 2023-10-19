package provider;

import com.alticon.cache.provider.redis.RedisCacheMan;
import config.RedisConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.embedded.RedisServer;


@SpringBootTest(classes = RedisConfiguration.class)
public class RedisCacheManTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    @InjectMocks
    private RedisCacheMan redisCacheMan;
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
    public void getTest() throws Exception {
        String key = "gtr";
        String value = "erf";
        if (redisTemplate != null) {
            ValueOperations<String, String> f = redisTemplate.opsForValue();
            System.out.println(f);
            try {
                redisTemplate.opsForValue().set(key, value);
                //Only mock methods is used in Mockito
//                when(redisTemplate.opsForValue().get(key)).thenReturn(value);
                String databaseValue = redisTemplate.opsForValue().get(key);
                if (databaseValue != null && databaseValue.equals(value)) {
//                    String expectedValue = redisCacheMan.get(key);
//                    Assertions.assertEquals(key, expectedValue);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new Exception();
            }
        }

    }
}
