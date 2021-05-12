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
public class StringRedisTemplateStream extends StringRedisTemplateFactory<List>{

    private final StreamOperations streamOperations;

    public StringRedisTemplateStream(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        streamOperations = getStringRedisTemplate().opsForStream();
    }

    @Override
    public List getValueByKey(String key) {
        return streamOperations.read(String.class, StreamOffset.fromStart(key));
    }

    @Override
    public boolean addValue(String key, List value) {
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
