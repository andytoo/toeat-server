package com.toeat.api.controller;

import com.google.gson.Gson;
import com.toeat.api.model.Item;
import com.toeat.api.model.Order;
import com.toeat.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private Gson gson;

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Optional<Order> saveOrder(@RequestBody Map<String, Object> orderMap) {
        String phone = (String) orderMap.get("phone");
        UUID restaurantId = UUID.fromString((String) orderMap.get("restaurantId"));
        List<Item> itemList = (List<Item>) orderMap.get("itemList");
        int total = (int) orderMap.get("total");
        return orderService.saveOrder(phone, restaurantId, itemList, total);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> findOrderById(@PathVariable("orderId") UUID orderId) {
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/all/{restaurantId}")
    public List<Order> getAllOrdersByRestaurantId(@PathVariable("restaurantId") UUID restaurantId) {
        return orderService.findAllOrdersByRestaurantId(restaurantId);
    }
}
