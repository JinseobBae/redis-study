package com.study.redisstudy.template;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StringRedisTemplateList extends StringRedisTemplateFactory<List> {

    public StringRedisTemplateList(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
    }

    @Override
    public List getValueByKey(String key) {
        ListOperations listOperations = getStringRedisTemplate().opsForList();
        long listSize = listOperations.size(key);
        List resultList = listOperations.range(key,0 , listSize -1);
        return resultList;
    }
    @Override
    public boolean addValue(String key, List value) {
        try{
            ListOperations listOperations = getStringRedisTemplate().opsForList();
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
        long listSize = getStringRedisTemplate().opsForList().size(key);
        if(listSize > 0){
            return getStringRedisTemplate().delete(key);
        }else{
            return false;
        }
    }
}
