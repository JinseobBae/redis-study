package com.study.redisstudy.service;

import java.util.HashMap;
import java.util.List;

public interface CacheService {

    List<HashMap<String,String>> select(Long id);

    HashMap<String, Object> save();

    HashMap<String, String> delete(Long id);
}
