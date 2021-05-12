package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class StringRedisTemplateString extends StringRedisTemplateFactory<String> {

    private final ValueOperations<String, String> valueOperations;

    public StringRedisTemplateString(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        valueOperations = getStringRedisTemplate().opsForValue();
    }

    @Override
    public String getValueByKey(String key) {
       String value = valueOperations.get(key);
       return value;
    }

    @Override
    public boolean addValue(String key, String value) {
        try{
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

    @Override
    public boolean deleteKey(String key) {
        String value = valueOperations.get(key);
        if(value != null){
            return getStringRedisTemplate().delete(key);
        }else{
            return false;
        }
    }
}
