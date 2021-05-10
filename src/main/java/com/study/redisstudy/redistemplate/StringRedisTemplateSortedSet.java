package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StringRedisTemplateSortedSet extends StringRedisTemplateFactory<Set<String>>{


    public StringRedisTemplateSortedSet(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }

    @Override
    public Set<String> getValueByKey(String key) {
        ZSetOperations<String,String> operations = getStringRedisTemplate().opsForZSet();
        Long size = operations.size(key);
        return operations.range(key,0, size);
    }

    @Override
    public boolean addValue(String key, Set<String> value) {
        ZSetOperations<String,String> operations = getStringRedisTemplate().opsForZSet();
        value.forEach(v -> operations.add(key,v, 1));
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        ZSetOperations<String,String> operations = getStringRedisTemplate().opsForZSet();
        Long size = operations.size(key);
        operations.removeRange(key, 0, size);
        return true;
    }
}
