package com.toeat.api.service;

import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Category;
import com.toeat.api.model.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {

    List<Restaurant> findAll();
    Optional<Restaurant> findById(UUID id);
    void updateRest(Restaurant restaurant);
    Category insertMenu(UUID id, Category category);
    Category updateMenu(UUID id, Category category);
    byte[] getImage(String fileName) throws ModuleException;
    byte[] getImage64(String fileName) throws ModuleException;
    void uploadImage(MultipartFile file, String fileName) throws ModuleException;

//    List<Restaurant> getRestaurants();
//    Restaurant saveRestaurant(Restaurant restaurant);
//    Restaurant editRestaurant(Restaurant restaurant);
}
