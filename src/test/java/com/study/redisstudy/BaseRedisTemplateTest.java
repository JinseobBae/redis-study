package com.study.redisstudy;

import org.slf4j.ILoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseRedisTemplateTest {

    protected void println(Object str){
        System.out.println(str);
    }
}
