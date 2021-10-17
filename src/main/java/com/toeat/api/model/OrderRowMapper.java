package com.toeat.api.model;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRowMapper implements RowMapper<Order> {

    @Autowired
    private Gson gson;

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(rs.getObject("id", UUID.class),
                         rs.getString("phone"),
                         rs.getObject("restaurantId", UUID.class),
                         this.jsonCartConverter(new JSONArray(rs.getString("itemList"))),
                         rs.getInt("total"),
                         rs.getTimestamp("date"));
    }

    private List<Item> jsonCartConverter(JSONArray cartArr) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < cartArr.length(); i++) {
            JSONObject cart =  cartArr.getJSONObject(i);
            String categoryName = cart.getString("name");
            int quantity = cart.getInt("quantity");
            int price = cart.getInt("price");
            itemList.add(new Item(categoryName, quantity, price));
        }
        return itemList;
    }
}
