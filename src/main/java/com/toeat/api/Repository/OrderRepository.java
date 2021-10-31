package com.toeat.api.Repository;

import com.toeat.api.model.Order;
import com.toeat.api.model.OrderRowMapper;
import com.toeat.api.model.Item;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID>, OrderCustomRepository {

    //User
//    @Query(value = "select rest.id, rest.name, orders.phone, orders.itemList from orders join restaurants rest on orders.restaurantid = rest.id where orders.phone = :phone", )
//    List<Map> findByPhone(String phone);

    // Client
//    @Query(value = "select * from orders", rowMapperClass = OrderRowMapper.class)
//    List<Order> findAll();

    @Query(value = "select * from orders where restaurantId = :restaurantId", rowMapperClass = OrderRowMapper.class)
    List<Order> findByRestaurantId(UUID restaurantId);

    @Query(value = "select * from orders where id = :id", rowMapperClass = OrderRowMapper.class)
    Optional<Order> findById(UUID id);
}
