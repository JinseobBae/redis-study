package com.study.redisstudy.redisrepository.repository;

import com.study.redisstudy.redisrepository.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRedisRepository extends CrudRepository<Food, Long> {

}
