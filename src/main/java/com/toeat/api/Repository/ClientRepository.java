package com.toeat.api.Repository;

import com.toeat.api.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String>, ClientCustomRepository {
    int countByPhone(String phone);
    Optional<Client> findByPhone(String phone);
}
