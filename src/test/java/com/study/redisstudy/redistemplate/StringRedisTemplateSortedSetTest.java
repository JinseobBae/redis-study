package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class StringRedisTemplateSortedSetTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateSortedSet stringRedisTemplateSortedSet;

    @Test
    public void testString(){
        String key = "SS TEST";
        Set<String> value = Stream.of("T","E","S","T").collect(Collectors.toSet());

        boolean addResult = stringRedisTemplateSortedSet.addValue(key, value); // 값 입력
        println(addResult);

        Set<String> resultValue = stringRedisTemplateSortedSet.getValueByKey(key); // 값 조회
        println(resultValue);

        boolean deleteResult = stringRedisTemplateSortedSet.deleteKey(key);
        println(deleteResult);

        assert !value.equals(resultValue);
        assert resultValue.size() == 3;
        assert deleteResult;
    }
}