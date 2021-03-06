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


    @GetMapping("select/{id}")
    ResponseEntity<Object> selectCache(@PathVariable("id") Long id){
        List<HashMap<String, String>> result = cacheService.select(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("save")
    ResponseEntity<Object> save(){
        HashMap<String, Object> result = cacheService.save();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("del/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") Long id){
        HashMap<String,String> result = cacheService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
