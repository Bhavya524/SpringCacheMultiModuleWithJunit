package com.alticon.cache.controller;

import com.alticon.cache.exceptions.CachingException;
import com.alticon.cache.model.CacheDemoDTO;
import com.alticon.cache.provider.CacheMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheDemoController {

    public CacheDemoController(CacheMan cacheMan) {
        this.cacheMan = cacheMan;
    }
    private CacheMan cacheMan;

    @PostMapping(value = "/set")
    public String set(@RequestBody CacheDemoDTO cacheDemoDTO) throws Exception {
        String response = null;
        try {
            return cacheMan.set(cacheDemoDTO.getKey(), cacheDemoDTO.getValue());
        } catch (CachingException cachingException) {
            throw cachingException;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping(value = "/get")
    public String get(@RequestBody CacheDemoDTO cacheDemoDTO) throws Exception {
        String response = null;
        try {
            return cacheMan.get(cacheDemoDTO.getKey());
        } catch (CachingException cachingException) {
            throw cachingException;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
