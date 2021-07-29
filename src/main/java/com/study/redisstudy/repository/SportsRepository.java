package com.study.redisstudy.repository;

import com.study.redisstudy.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsRepository extends JpaRepository<Sports, Long> {


}
