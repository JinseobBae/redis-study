package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class StringRedisTemplateSetTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateSet stringRedisTemplateSet;

    @Test
    public void testString(){
        String key = "REDIS SET";
        Set<String> value = Stream.of("T","E","S","T").collect(Collectors.toSet());

        boolean addResult = stringRedisTemplateSet.addValue(key, value); // 값 입력
        println(addResult);

        Set<String> resultValue = stringRedisTemplateSet.getValueByKey(key); // 값 조회
        println(resultValue);

        boolean deleteResult = stringRedisTemplateSet.deleteKey(key);
        println(deleteResult);

        assert value.equals(resultValue);
        assert deleteResult;
    }
}