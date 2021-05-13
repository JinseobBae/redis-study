package com.study.redisstudy.redistemplate;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class StringRedisTemplateHashTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateHash stringRedisTemplateHash;



    @Test
    public void testStringList(){
        String key = "TEST_STRING_LIST";

        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        hashMap.put("key4", "value4");

        boolean addResult = stringRedisTemplateHash.addValue(key, hashMap); // 값 입력

        assert addResult;


        HashMap<Object,Object> result  =  stringRedisTemplateHash.getValueByKey(key); // 깂 조회
        printResult(key, result);

        boolean deleteResult = stringRedisTemplateHash.deleteKey(key);

        assert !result.isEmpty();
        assert deleteResult;
        assert hashMap.size() == result.size();
        assert result.get("key1") != null;
        assert hashMap.get("key1").equals(result.get("key1"));
    }

    private void printResult(String key, HashMap<Object, Object> result){
        println("< Result [Key:" + key + "] >");
        result.forEach((k,v) -> println("Map [key : " + k + ", value : " + v + "]"));
    }
}