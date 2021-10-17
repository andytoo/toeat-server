package com.toeat.api.Repository;

import com.toeat.api.model.User;

import java.util.UUID;

public interface UserCustomRepository {
    User save(String phone, String name, String password, UUID restaurantId);
}
