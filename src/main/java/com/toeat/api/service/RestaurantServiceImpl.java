package com.toeat.api.service;

import com.google.gson.Gson;
import com.toeat.api.Repository.RestaurantRepository;
import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Category;
import com.toeat.api.model.Restaurant;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private Gson gson;

    @Autowired
    private Environment env;

    @Autowired
    private RestaurantRepository restaurantRepository;

    //TODO MOVE SOMEWHERE ELSE(MODULE/TOOL)
    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    @Override
    public Optional<Restaurant> findById(UUID id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public void updateRest(Restaurant restaurant) {
        Optional<Restaurant> rest = restaurantRepository.findByName(restaurant.getName());

        if (rest.isPresent() && !rest.get().getId().equals(restaurant.getId()))
            throw new ModuleException(restaurant.getName() + " is already taken");
        if (!restaurantRepository.updateRest(restaurant.getName(), restaurant.getCity(), restaurant.getInfo(), restaurant.getId())) {
            throw new ModuleException("update" + restaurant.getName() + " error");
        }
    }

    @Override
    public Category insertMenu(UUID id, Category category) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            List<Category> categoryList = restaurant.get().getCategoryList();
            categoryList.add(category);
            restaurantRepository.updateRest(restaurant.get());
            return category;
        } else {
            throw new ModuleException("restaurant is not exist!");
        }
    }

    @Override
    public Category updateMenu(UUID id, Category category) throws ModuleException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            List<Category> categoryList = restaurant.get().getCategoryList();
            for (Category cat : categoryList) {
                if (cat.getId().equals(category.getId())) {
                    cat.setName(category.getName());
                    cat.setMenuList(category.getMenuList());
                    restaurantRepository.updateRest(restaurant.get());
                    return cat;
                }
            }
            throw new ModuleException("update " + category.getName() + " error");
        } else {
            throw new ModuleException("restaurant is not exist!");
        }
    }

    @Override
    public byte[] getImage(String fileName) throws ModuleException {
        String filePath = env.getProperty("toeat.restaurant.imageFile") + "/";
        File image = new File(filePath + "/" + fileName + "/" + fileName + ".jpeg");//TODO FILE EXT

        try {
            if (image.exists()) {
                return Files.readAllBytes(image.toPath());
            } else {
                File defaultImage = new File(filePath + "/image-not-available.jpeg");
                return Files.readAllBytes(defaultImage.toPath());
            }
        } catch (IOException ioe) {
            throw new ModuleException("Reading image file error" + " " + ioe.getMessage());
        }
    }

    @Override
    public byte[] getImage64(String fileName) throws ModuleException {
        String filePath = env.getProperty("toeat.restaurant.imageFile") + "/";
        File image = new File(filePath + "/" + fileName + "/" + fileName + ".jpeg");//TODO FILE EXT

        try {
            if (image.exists()) {
                return Base64.getEncoder().encode(Files.readAllBytes(image.toPath()));
            } else {
                File defaultImage = new File(filePath + "/image-not-available.jpeg");
                return Base64.getEncoder().encode(Files.readAllBytes(defaultImage.toPath()));
            }
        } catch (IOException ioe) {
            throw new ModuleException("Reading image file error" + " " + ioe.getMessage());
        }
    }

    @Override
    public void uploadImage(MultipartFile file, String fileName) throws ModuleException {

        File filePath = new File(env.getProperty("toeat.restaurant.imageFile") + "/" + fileName);
        if (filePath.exists() && filePath.isDirectory()) {
            filePath.delete();
        }

        Optional<String> fileExtension = this.getExtensionByStringHandling(file.getOriginalFilename());
        if (fileExtension.isPresent()) {
            fileName += "." + fileExtension.get();
        }

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        filePath = new File(filePath + "/" + fileName);

        try {
            file.transferTo(filePath);
        } catch (IOException ioe) {
            throw new ModuleException("Upload Image Failed" + " " + ioe.getMessage());
        }
    }

//    @Override
//    public List<Restaurant> getRestaurants() {
//        log.info("Fetching all users");
//        return restaurantRepository.findAll();
//    }

//    @Override
//    public Restaurant saveRestaurant(Restaurant restaurant) {
//        log.info("Saving new restaurant {} to the database", restaurant.getName());
//        return restaurantRepository.save(restaurant);
//    }

//    @Override
//    public Restaurant editRestaurant(Restaurant restaurant) {
//        if (restaurantRepository.findById(restaurant.getId()).isPresent()) {
//            try {
//                return restaurantRepository.save(restaurant);
//            } catch (Exception e) {
//                log.info("Updating restaurant {} error: {}", restaurant.getName(), e.getMessage());
//            }
//        } else {
//            log.info("restaurant {} not found", restaurant.getName());
//        }
//        return restaurant;
//    }
}
