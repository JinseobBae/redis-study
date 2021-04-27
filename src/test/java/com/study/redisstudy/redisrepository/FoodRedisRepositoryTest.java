package com.study.redisstudy.redisrepository;

import com.study.redisstudy.BaseRedisRepositoryTest;
import com.study.redisstudy.redisrepository.model.Food;
import com.study.redisstudy.redisrepository.repository.FoodRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FoodRedisRepositoryTest extends BaseRedisRepositoryTest {

    @Autowired
    FoodRedisRepository foodRedisRepository;

    @Test
    void saveTest(){
        Food friedRice = new Food("rice", "friedRice", 10000);
        Food saveResult = foodRedisRepository.save(friedRice);

        Optional<Food> searchResult = foodRedisRepository.findById(saveResult.getId());

        assert searchResult.isPresent();
        assert searchResult.get().getType().equals("rice");
        assert searchResult.get().getName().equals("friedRice");
        assert searchResult.get().getPrice() == 10000;
    }
}
