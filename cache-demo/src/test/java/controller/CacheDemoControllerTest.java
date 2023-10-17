package controller;

import com.alticon.cache.controller.CacheDemoController;
import com.alticon.cache.model.CacheDemoDTO;
import com.alticon.cache.provider.CacheMan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CacheDemoControllerTest {

    @InjectMocks
    CacheDemoController cacheDemoController;

    @Mock
    CacheMan cacheMan;

    @Test
    public void getCacheTest() throws Exception {

        CacheDemoDTO cacheDemoDTO = new CacheDemoDTO("first");
        when(cacheMan.get(cacheDemoDTO.getKey())).thenReturn("first, first value");
        String value = cacheDemoController.get(cacheDemoDTO);
        Assertions.assertEquals("first, first value", value);

    }
    @Test
    public void setCacheTest() throws Exception {

        CacheDemoDTO cacheDemoDTO = new CacheDemoDTO("second","second value");
        when(cacheMan.set(cacheDemoDTO.getKey(), cacheDemoDTO.getValue())).thenReturn(cacheDemoDTO.getKey() +", "+cacheDemoDTO.getValue());
        String value = cacheDemoController.set(cacheDemoDTO);
        Assertions.assertEquals(cacheDemoDTO.getKey() +", "+cacheDemoDTO.getValue(), value);

    }
}
