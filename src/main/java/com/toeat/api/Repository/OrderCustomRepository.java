package com.toeat.api.Repository;

import com.toeat.api.model.Item;

import java.util.List;
import java.util.UUID;

public interface OrderCustomRepository {
    UUID save(UUID id, String phone, UUID restaurantId, List<Item> itemList, int total);
}
