package com.alticon.cache.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CacheDemoDTO {
    public CacheDemoDTO(@JsonProperty("key") String key) {
        this.key = key;
    }

    private String key;
    private String value;
}
