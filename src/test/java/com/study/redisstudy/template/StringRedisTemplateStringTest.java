package com.study.redisstudy.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StringRedisTemplateStringTest {

    @Autowired
    StringRedisTemplateString stringRedisTemplateString;

    @Test
    public void testString(){
        String key = "TEST";
        String value = "REDIS TEST";

        boolean addResult = stringRedisTemplateString.addValue(key, value);
        System.out.println(addResult);

        String resultValue = stringRedisTemplateString.getValueByKey(key);
        System.out.println(resultValue);

        assert value.equals(resultValue);
    }
}