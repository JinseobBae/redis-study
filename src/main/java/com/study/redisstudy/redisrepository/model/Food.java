package com.study.redisstudy.redisrepository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("food")
public class Food {

    protected Food(){}
    public Food(String type, String name, long price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Id
    long id;

    String type;
    String name;
    long price;


    public void changeName(String name){
        this.name = name;
    }

    public long getId() { return id; }

    public String getType() { return type; }

    public String getName() { return name;}

    public long getPrice() { return price; }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
