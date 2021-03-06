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

    //User
    @GetMapping("/phone/{phone}")
    public List<Map<String, Object>> getOrderByPhone(@PathVariable("phone") String phone) {
        return orderService.getOrdersByPhone(phone);
    }

    //Client
    @GetMapping("/all/{restaurantId}")
    public List<Order> getAllOrdersByRestaurantId(@PathVariable("restaurantId") UUID restaurantId) {
        return orderService.findAllOrdersByRestaurantId(restaurantId);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> findOrderById(@PathVariable("orderId") UUID orderId) {
        return orderService.findOrderById(orderId);
    }

    @PostMapping("/save")
    public Optional<Order> saveOrder(@RequestBody Map<String, Object> orderMap) {
        String phone = (String) orderMap.get("phone");
        UUID restaurantId = UUID.fromString((String) orderMap.get("restaurantId"));
//        String status = (String) orderMap.get("status");
        String status = "00";
        List<Item> itemList = (List<Item>) orderMap.get("itemList");
        int total = (int) orderMap.get("total");

        Optional<Order> order = orderService.saveOrder(phone, restaurantId, status, itemList, total);
        return order;
    }

    @PostMapping("/update")
    public Optional<Order> updateOrder(@RequestBody Map<String, Object> orderMap) {
        UUID id = UUID.fromString((String) orderMap.get("id"));
        String status = (String) orderMap.get("status");
        Optional<Order> order = orderService.updateOrder(id, status);
        return order;
    }
}
