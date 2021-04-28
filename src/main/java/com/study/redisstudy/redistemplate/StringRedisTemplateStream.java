package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Redis 5.0 + 부터 가능
 */
@Service
public class StringRedisTemplateStream extends StringRedisTemplateFactory<List>{

    public StringRedisTemplateStream(StringRedisTemplate stringRedisTemplate) { super(stringRedisTemplate); }

    @Override
    public List getValueByKey(String key) {
        StreamOperations streamOperations = getStringRedisTemplate().opsForStream();
        return streamOperations.read(String.class, StreamOffset.fromStart(key));
    }

    @Override
    public boolean addValue(String key, List value) {
        StreamOperations streamOperations = getStringRedisTemplate().opsForStream();
        try{
            AtomicInteger index = new AtomicInteger();
            value.stream().forEach( v -> {
                        String id =  String.valueOf(key.length()) + "-" + String.valueOf(index.getAndIncrement());
                        ObjectRecord<String, Object> objectRecord = StreamRecords.newRecord()
                                .in(key)
                                .withId(id)
                                .ofObject(v);
                        streamOperations.add(objectRecord);
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        StreamOperations streamOperations = getStringRedisTemplate().opsForStream();
        try{
            List<ObjectRecord> valueList = getValueByKey(key);
            valueList.forEach( v -> streamOperations.delete(v));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
