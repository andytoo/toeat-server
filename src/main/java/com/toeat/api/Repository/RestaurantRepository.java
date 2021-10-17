package com.toeat.api.Repository;

import com.toeat.api.model.Restaurant;
import com.toeat.api.model.RestaurantRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, UUID>, RestaurantCustomRepository {

    @Query(value = "select * from restaurants", rowMapperClass = RestaurantRowMapper.class)
    List<Restaurant> findAll();

    @Query(value = "select * from restaurants where name = :name", rowMapperClass = RestaurantRowMapper.class)
    Optional<Restaurant> findByName(String name);

    @Query(value = "select * from restaurants where id = :id", rowMapperClass = RestaurantRowMapper.class)
    Optional<Restaurant> findById(UUID id);

    @Modifying
    @Query(value = "update restaurants set name = :name, city = :city, info = :info where id = :id", rowMapperClass = RestaurantRowMapper.class)
    boolean updateRest(String name, String city, String info, UUID id);
}
