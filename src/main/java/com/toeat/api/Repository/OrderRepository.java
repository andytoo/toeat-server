package com.toeat.api.Repository;

import com.toeat.api.model.Order;
import com.toeat.api.model.OrderRowMapper;
import com.toeat.api.model.Item;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @Query(value = "select * from orders", rowMapperClass = OrderRowMapper.class)
    List<Order> findAll();
    UUID save(UUID id, String phone, UUID restaurantId, List<Item> itemList, int total);
    @Query(value = "select * from orders where id = :id", rowMapperClass = OrderRowMapper.class)
    Optional<Order> findById(UUID id);
    @Query(value = "select * from orders where restaurantId = :restaurantId", rowMapperClass = OrderRowMapper.class)
    List<Order> findByRestaurantId(UUID restaurantId);
}
