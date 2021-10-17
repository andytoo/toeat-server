package com.toeat.api.service;

import com.toeat.api.model.Item;
import com.toeat.api.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<Order> saveOrder(String phone, UUID restaurantId, List<Item> itemList, int total);
    Optional<Order> findOrderById(UUID id);
    List<Order> findAllOrdersByRestaurantId(UUID restaurantId);
}
