package com.toeat.api.service;

import com.toeat.api.Repository.OrderRepository;
import com.toeat.api.model.Item;
import com.toeat.api.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> saveOrder(String phone, UUID restaurantId, List<Item> itemList, int total) {
        UUID orderId = orderRepository.save(UUID.randomUUID(), phone, restaurantId, itemList, total);//TODO
        return orderRepository.findById(orderId);
    }

    @Override
    public Optional<Order> findOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllOrdersByRestaurantId(UUID restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }
}
