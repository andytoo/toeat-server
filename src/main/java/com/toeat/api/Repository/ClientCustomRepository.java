package com.toeat.api.Repository;

import com.toeat.api.model.Client;

import java.util.UUID;

public interface ClientCustomRepository {
    Client save(String phone, String name, String password, UUID restaurantId);
}
