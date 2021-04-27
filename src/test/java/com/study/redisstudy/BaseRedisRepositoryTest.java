package com.study.redisstudy;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseRedisRepositoryTest {

    protected void println(Object str){
        System.out.println(str);
    }
}
