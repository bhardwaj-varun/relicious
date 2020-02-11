package com.varun.springboot.relicious.controller;

import com.varun.springboot.relicious.entity.RestaurantEntity;
import com.varun.springboot.relicious.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurants" , produces = "application/hal+json")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/")
    List<RestaurantEntity> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<RestaurantEntity> getRestaurant(@PathVariable("id") Long id){
        return restaurantRepository.findById(id);
    }
    @PostMapping("/")
    RestaurantEntity addRestaurant(@RequestBody RestaurantEntity entity){
        return restaurantRepository.save(entity);
    }

    @PutMapping("/{id}")
    RestaurantEntity updateRestaurant(@RequestBody RestaurantEntity entity, @PathVariable("id") Long id){
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(entity.getName());
                    return restaurantRepository.save(restaurant);
                })
                .orElseGet(()->{
                    entity.setId(id);
                    return restaurantRepository.save(entity);
                });
    }
    @DeleteMapping("/{id}")
    void closeRestaurant(@PathVariable("id") Long  id){
        restaurantRepository.deleteById(id);
    }
}
