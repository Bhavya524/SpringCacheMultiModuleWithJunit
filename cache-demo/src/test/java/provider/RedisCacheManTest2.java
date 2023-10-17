package provider;

import com.alticon.cache.provider.redis.RedisCacheMan;
import config.RedisConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(classes = RedisConfiguration.class)
public class RedisCacheManTest2 {

    @InjectMocks
    private RedisCacheMan redisCacheMan;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @BeforeEach
    public void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void testGetValue() throws Exception {
        String key = "testKey";
        String expectedValue = "testValue";
//        redisCacheMan.set(key, expectedValue);
        when(redisTemplate.opsForValue().get(key)).thenReturn(key +", "+ expectedValue);

        String actualValue = redisCacheMan.get(key);

        Assertions.assertEquals(key +", "+ expectedValue, actualValue);
    }
}
