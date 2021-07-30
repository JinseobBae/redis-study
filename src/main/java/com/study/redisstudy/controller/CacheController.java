package com.study.redisstudy.controller;

import com.study.redisstudy.service.CacheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cache/")
public class CacheController {

    private final CacheService cacheService;

    CacheController(CacheService cacheService){
        this.cacheService = cacheService;
    }


    @GetMapping("select")
    ResponseEntity<Object> selectCache(){
        List<HashMap<String, String>> result = cacheService.select();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("save")
    ResponseEntity<Object> save(){
        HashMap<String, Object> result = cacheService.save();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
