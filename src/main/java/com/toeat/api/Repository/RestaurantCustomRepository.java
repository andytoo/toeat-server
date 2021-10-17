package com.toeat.api.Repository;

import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Restaurant;

import java.util.UUID;

public interface RestaurantCustomRepository {
    UUID createRest(UUID restaurantId);
    void updateRest(Restaurant restaurant) throws ModuleException;
}
