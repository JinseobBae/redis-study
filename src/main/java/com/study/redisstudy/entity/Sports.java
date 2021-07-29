package com.study.redisstudy.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Sports {

    @Id
    long id;

    String type;
    String name;


}
