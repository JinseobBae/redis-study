package com.study.redisstudy.template;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class StringRedisTemplateString extends StringRedisTemplateFactory<String> {

    public StringRedisTemplateString(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }

    @Override
    public String getValueByKey(String key) {
       ValueOperations<String, String> valueOperations = getStringRedisTemplate().opsForValue();
       String value = valueOperations.get(key);
       return value;
    }

    @Override
    public boolean addValue(String key, String value) {
        try{
            ValueOperations<String, String> valueOperations = getStringRedisTemplate().opsForValue();
            valueOperations.set(key,value);
        }catch(RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return false;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
