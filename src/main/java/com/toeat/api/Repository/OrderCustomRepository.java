package com.toeat.api.Repository;

import com.toeat.api.model.Item;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrderCustomRepository {

    //User
    List<Map<String, Object>> findByPhone(String phone);

    //Client
    UUID save(UUID id, String phone, UUID restaurantId, List<Item> itemList, int total);
}
