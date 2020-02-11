package com.varun.springboot.relicious.repository;

import com.varun.springboot.relicious.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
