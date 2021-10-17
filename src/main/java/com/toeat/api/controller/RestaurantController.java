package com.toeat.api.controller;

import com.google.gson.Gson;
import com.toeat.api.model.Category;
import com.toeat.api.model.Restaurant;
import com.toeat.api.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private Gson gson;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{restaurantId}")
    public Optional<Restaurant> findOrderById(@PathVariable("restaurantId") UUID id) {
        return restaurantService.findById(id);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.updateRest(restaurant);
    }

    @PostMapping("/insertMenu/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Category insertRestaurantMenu(@PathVariable("restaurantId") UUID id, @RequestBody Category category) {
        return restaurantService.insertMenu(id, category);
    }

    @PostMapping("/updateMenu/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Category updateRestaurantMenu(@PathVariable("restaurantId") UUID id, @RequestBody Category category) {
        return restaurantService.updateMenu(id, category);
    }

    @GetMapping("/image/{restaurantId}")
    public ResponseEntity<?> getImage(@PathVariable("restaurantId") UUID id) {
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(restaurantService.getImage(id));
        return ResponseEntity.ok().body(restaurantService.getImage(id.toString()));
    }

    @GetMapping("/image64/{restaurantId}")
    public ResponseEntity<?> getImage64(@PathVariable("restaurantId") UUID id) {
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(restaurantService.getImage(id));
        return ResponseEntity.ok().body(restaurantService.getImage64(id.toString()));
    }

    @PostMapping("/upload/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> uploadImage(@PathVariable("restaurantId") UUID id, @RequestParam("file") MultipartFile file) {
        restaurantService.uploadImage(file, id.toString());
        return ResponseEntity.ok("File uploaded successfully");//TODO
    }
}
