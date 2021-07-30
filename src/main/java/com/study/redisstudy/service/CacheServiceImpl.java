package com.study.redisstudy.service;

import com.study.redisstudy.entity.Sports;
import com.study.redisstudy.repository.SportsRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CacheServiceImpl implements CacheService {

    private final SportsRepository sportsRepository;

    CacheServiceImpl(SportsRepository sportsRepository){
        this.sportsRepository = sportsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "select")
    public List<HashMap<String, String>> select() {

        List<Sports> result = sportsRepository.findAll();

        return result.stream()
                .map( sports -> {
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put(sports.getName(), sports.getType());
                    return hashMap;
                })
                .collect(Collectors.toList());

    }

    @Override
    @CachePut(value = "select")
    public HashMap<String, Object> save() {
        Sports sports = Sports.builder()
                .type("ball")
                .name("baseball")
                .build();
        Sports result = sportsRepository.save(sports);
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("result", result);

        return resultMap;
    }
}
