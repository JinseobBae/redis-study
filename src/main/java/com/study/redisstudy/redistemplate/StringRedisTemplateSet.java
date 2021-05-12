package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StringRedisTemplateSet extends StringRedisTemplateFactory<Set<String>>{

    private final SetOperations<String, String> setOperations;

    public StringRedisTemplateSet(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        setOperations = getStringRedisTemplate().opsForSet();
    }

    @Override
    public Set<String> getValueByKey(String key) {
        return setOperations.members(key);
    }

    @Override
    public boolean addValue(String key, Set<String> value) {
        value.forEach( v -> setOperations.add(key,v));
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        Set<String> value = getValueByKey(key);
        setOperations.remove(key, value);
        return true;
    }
}
