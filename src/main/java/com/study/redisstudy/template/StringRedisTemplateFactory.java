package com.study.redisstudy.template;


import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class StringRedisTemplateFactory<T> {

    private final StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplateFactory(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return this.stringRedisTemplate;
    }

    public abstract T getValueByKey(String key);

    public abstract boolean addValue(String key, T value);

}

