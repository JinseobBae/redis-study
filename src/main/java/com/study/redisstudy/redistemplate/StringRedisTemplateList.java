package com.study.redisstudy.redistemplate;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StringRedisTemplateList extends StringRedisTemplateFactory<List<String>> {

    private final ListOperations<String, String> listOperations ;

    public StringRedisTemplateList(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        listOperations = getStringRedisTemplate().opsForList();
    }

    @Override
    public List<String> getValueByKey(String key) {
        long listSize = listOperations.size(key);
        return listOperations.range(key,0 , listSize -1);
    }
    @Override
    public boolean addValue(String key, List<String> value) {
        try{
            value.stream().forEach( v -> listOperations.rightPush(key, v));
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
        long listSize = listOperations.size(key);
        if(listSize > 0){
            return getStringRedisTemplate().delete(key);
        }else{
            return false;
        }
    }
}
