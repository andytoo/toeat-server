package com.toeat.api.Repository;

import com.toeat.api.exceptions.ModuleException;
import com.toeat.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class UserRepositoryImpl implements UserCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into users(phone, name, password, restaurantId) values (?, ?, ?, ?)";

    @Override
    public User save(String phone, String name, String password, UUID restaurantId) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, phone);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setObject(4, restaurantId);
                return ps;
            }, keyHolder);
            return new User((String) keyHolder.getKeys().get("phone"), (String) keyHolder.getKeys().get("name"), (String) keyHolder.getKeys().get("password"), (UUID) keyHolder.getKeys().get("restaurantId"));
        } catch (Exception e) {
            throw new ModuleException("create user failed: " + e.getMessage());
        }
    }
}
