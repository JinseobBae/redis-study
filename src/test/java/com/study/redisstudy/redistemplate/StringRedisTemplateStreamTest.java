package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;

import java.util.ArrayList;
import java.util.List;


class StringRedisTemplateStreamTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateStream stringRedisTemplateStream;

    @Test
    public void testRedisStream(){
        String key = "RSTREAM";

        List<ObjectRecord<String, Object>> values = new ArrayList<>();
        int id = 0;
        ObjectRecord<String, Object> record01 = StreamRecords.newRecord()
                .in(key)
                .withId(String.valueOf(++id))
                .ofObject("VALUE01");

        values.add(record01);

        ObjectRecord<String, Object> record02 = StreamRecords.newRecord()
                .in(key)
                .withId(String.valueOf(++id))
                .ofObject("VALUE02");

        values.add(record02);

        boolean addResult = stringRedisTemplateStream.addValue(key, values);
        assert addResult;

        List result = stringRedisTemplateStream.getValueByKey(key);
        result.forEach(System.out::println);

        assert values.size() == result.size();


    }
}