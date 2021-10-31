package com.toeat.api.service;

import com.toeat.api.model.Item;
import com.toeat.api.model.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    //User
    List<Map<String, Object>> getOrdersByPhone(String phone);

    // Client
    List<Order> findAllOrdersByRestaurantId(UUID restaurantId);
    Optional<Order> findOrderById(UUID id);
    Optional<Order> saveOrder(String phone, UUID restaurantId, List<Item> itemList, int total);

}
