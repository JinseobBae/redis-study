package com.study.redisstudy.template;

import com.study.redisstudy.BaseRedisTemplateTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class StringRedisTemplateStringTest extends BaseRedisTemplateTest {

    @Autowired
    StringRedisTemplateString stringRedisTemplateString;

    @Test
    public void testString(){
        String key = "TEST";
        String value = "REDIS TEST";

        boolean addResult = stringRedisTemplateString.addValue(key, value); // 값 입력
        println(addResult);

        String resultValue = stringRedisTemplateString.getValueByKey(key); // 값 조회
        println(resultValue);

        boolean deleteResult = stringRedisTemplateString.deleteKey(key);
        println(deleteResult);

        assert value.equals(resultValue);
        assert deleteResult;
    }
}