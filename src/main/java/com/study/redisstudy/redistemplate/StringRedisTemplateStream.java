package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Redis 5.0 + 부터 가능
 */
@Service
public class StringRedisTemplateStream extends StringRedisTemplateFactory<List<ObjectRecord<String, Object>>> {

    private final StreamOperations<String, String, Object> streamOperations;

    public StringRedisTemplateStream(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        streamOperations = getStringRedisTemplate().opsForStream();
    }

    @Override
    public List<ObjectRecord<String, Object>> getValueByKey(String key) {
        return streamOperations.read(Object.class, StreamOffset.fromStart(key));
    }

    @Override
    public boolean addValue(String key, List<ObjectRecord<String, Object>> value) {
        try{
            value.stream().forEach(streamOperations::add);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        try{
            List<ObjectRecord<String, Object>> valueList = getValueByKey(key);
            valueList.forEach( v -> streamOperations.delete(v));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
