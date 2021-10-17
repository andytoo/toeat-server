package com.toeat.api.Repository;

import com.toeat.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, UserCustomRepository {
    int countByPhone(String phone);
    User findByPhone(String phone);
}
