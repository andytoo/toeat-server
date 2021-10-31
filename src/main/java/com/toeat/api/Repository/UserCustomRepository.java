package com.toeat.api.Repository;

import com.toeat.api.model.User;

public interface UserCustomRepository {
    User save(String phone, String name, String password);
}
