package com.toeat.api.Repository;

import com.google.gson.Gson;
import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderCustomRepository {

    @Autowired
    private Gson gson;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    //User
    private final static String SQL_QUERY = "select orders.id orderId, rest.id restaurantId, rest.name restaurantName, orders.phone phone, orders.itemList itemList, orders.total total from orders join restaurants rest on orders.restaurantid = rest.id where orders.phone = ?";

    //Client
    private final static String SQL_INSERT = "insert into orders (id, phone, restaurantId, itemList, total, date) values (?, ?, ?, to_json(?::json), ?, ?)";

    //User
    @Override
    public List<Map<String, Object>> findByPhone(String phone) {
        return jdbcTemplate.queryForList(SQL_QUERY, new String[]{phone});
    }

    //Client
    @Override
    public UUID save(UUID id, String phone, UUID restaurantId, List<Item> itemList, int total) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, id);
                ps.setString(2, phone);
                ps.setObject(3, restaurantId);
                ps.setString(4, gson.toJson(itemList));
                ps.setInt(5, total);
                ps.setTimestamp(6, this.getCurrentTimestamp());
                return ps;
            }, keyHolder);
            return (UUID) keyHolder.getKeys().get("id");
        } catch (Exception e) {
            throw new ModuleException("create order failed: " + e.getMessage());
        }
    }
}
