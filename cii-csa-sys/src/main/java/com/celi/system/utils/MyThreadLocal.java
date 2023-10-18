package com.celi.system.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MyThreadLocal {

    private Map<String, Object> map;

    public Object getValue(String key) {
        if (map == null) {
            map = new HashMap<>();
        }

        return map.get(key);
    }

    public void setValue(String key, Object value) {
        if (map == null) {
            map = new HashMap<>();
        }

        map.put(key, value);
    }
}
