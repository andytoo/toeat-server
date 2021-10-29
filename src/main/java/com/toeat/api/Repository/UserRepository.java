package com.toeat.api.Repository;

import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String>, UserCustomRepository {
    int countByPhone(String phone);
    Optional<User> findByPhone(String phone);
}
