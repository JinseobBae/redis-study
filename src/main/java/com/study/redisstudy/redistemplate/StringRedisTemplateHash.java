package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StringRedisTemplateHash extends StringRedisTemplateFactory<HashMap<Object, Object>>{


    private final HashOperations<String, Object, Object> operations;

    public StringRedisTemplateHash(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        operations = getStringRedisTemplate().opsForHash();
    }

    @Override
    public HashMap<Object, Object> getValueByKey(String key) {
        return (HashMap<Object, Object>) operations.entries(key);
    }

    @Override
    public boolean addValue(String key, HashMap<Object, Object> value) {
        boolean result = false;
        try{
            value.forEach( (k, v) -> { operations.put(key, k, v); });
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteKey(String key) {
        HashMap<Object,Object> data = getValueByKey(key);
        Object[] mapKeys = data.keySet().toArray();
        operations.delete(key, mapKeys);
        return true;
    }
}
