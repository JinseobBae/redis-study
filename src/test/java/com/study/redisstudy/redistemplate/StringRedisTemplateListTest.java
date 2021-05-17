package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class StringRedisTemplateListTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateList stringRedisTemplateList;


    @Test
    public void testStringList(){
        String key = "TEST_STRING_LIST";

        List<String> stringList =  Stream.of("S", "T", "R", "I", "N", "G").collect(Collectors.toList());
        stringRedisTemplateList.addValue(key, stringList); // 값 입력

        List<String> result =  stringRedisTemplateList.getValueByKey(key); // 깂 조회
        printResult(key, result);

        boolean deleteResult = stringRedisTemplateList.deleteKey(key);

        assert !result.isEmpty();
        assert stringList.size() == result.size();
        assert deleteResult;
    }

    private void printResult(String key, List result){
        println("< Result [Key:" + key + "] >");
        println(Arrays.toString(result.toArray()));
    }
}