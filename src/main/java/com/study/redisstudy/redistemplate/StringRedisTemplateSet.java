package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StringRedisTemplateSet extends StringRedisTemplateFactory<Set<String>>{

    public StringRedisTemplateSet(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }

    @Override
    public Set<String> getValueByKey(String key) {
        SetOperations<String, String> setOperations = getStringRedisTemplate().opsForSet();
        return setOperations.members(key);
    }

    @Override
    public boolean addValue(String key, Set<String> value) {
        SetOperations<String, String> setOperations = getStringRedisTemplate().opsForSet();
        value.forEach( v -> setOperations.add(key,v));
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        SetOperations<String, String> setOperations = getStringRedisTemplate().opsForSet();
        Set<String> value = getValueByKey(key);
        setOperations.remove(key, value);
        return true;
    }
}
