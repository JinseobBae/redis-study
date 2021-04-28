package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


class StringRedisTemplateStreamTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateStream stringRedisTemplateStream;

    @Test
    public void testRedisStream(){
        String key = "RSTREAM";
        List value = new ArrayList();

        value.add("value1");
        value.add("value2");
        value.add("value3");

        boolean addResult = stringRedisTemplateStream.addValue(key, value);
        assert addResult;

        List result = stringRedisTemplateStream.getValueByKey(key);
        result.forEach(System.out::println);

        assert result.size() == value.size();
    }
}