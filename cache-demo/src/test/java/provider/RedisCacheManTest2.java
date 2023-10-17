package provider;

import com.alticon.cache.provider.redis.RedisCacheMan;
import config.RedisConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(classes = RedisConfiguration.class)
public class RedisCacheManTest2 {

    @InjectMocks
    private RedisCacheMan redisCacheMan;

    @Mock
    private RedisTemplate<String, String> redisTemplate;
    @Mock
    private RedisConnectionFactory redisConnectionFactory;


    @Mock
    private ValueOperations<String, String> valueOperations;

    @BeforeEach
    public void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void testGetValue() throws Exception {
        String key = "testKey";
        String value = "testValue";
        when(redisTemplate.opsForValue().get(key)).thenReturn(value);

        String actualValue = redisCacheMan.get(key);

        Assertions.assertEquals(value, actualValue);
    }
    @Test
    public void testSetValue() {
        String key = "testKey";
        String value = "testValue";
        redisTemplate.opsForValue().set("key", "value");
        verify(redisConnectionFactory.getConnection()).set(key.getBytes(), value.getBytes());
    }
}
