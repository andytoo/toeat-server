package com.toeat.api.Repository;

import com.google.gson.Gson;
import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class RestaurantRepositoryImpl implements RestaurantCustomRepository {

    @Autowired
    private Gson gson;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into restaurants(id) values (?)";

    private static final String SQL_UPDATE = "update restaurants set categoryList = to_json(?::json) where id = ?";

    @Override
    public UUID createRest(UUID restaurantId) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, restaurantId);
                return ps;
            }, keyHolder);
            return (UUID) keyHolder.getKeys().get("id");
        } catch (Exception e) {
            throw new ModuleException("create restaurant failed: " + e.getMessage());
        }
    }

    @Override
    public void updateRest(Restaurant restaurant) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gson.toJson(restaurant.getCategoryList()));
                ps.setObject(2, restaurant.getId());
                return ps;
            }, keyHolder);
        } catch (Exception e) {
            throw new ModuleException("update restaurant failed: " + e.getMessage());
        }
    }
}
