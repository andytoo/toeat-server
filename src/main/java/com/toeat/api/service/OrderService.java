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
    void notifyRestaurant(UUID restaurantId, Order order);

    // Client
    List<Order> findAllOrdersByRestaurantId(UUID restaurantId);
    Optional<Order> findOrderById(UUID id);
    Optional<Order> saveOrder(String phone, UUID restaurantId, String status, List<Item> itemList, int total);
    Optional<Order> updateOrder(UUID id, String status);
    void notifyUser(Order order);
}
