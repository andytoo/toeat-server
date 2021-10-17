package com.toeat.api.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantRowMapper implements RowMapper<Restaurant> {

    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Category> categoryList;

        if (rs.getObject("categoryList") == null) {
            categoryList = new ArrayList<>();
        } else {
            categoryList = this.jsonCategoryConverter(new JSONArray(rs.getString("categoryList")));
        }

        return new Restaurant(rs.getObject("id", UUID.class),
                              rs.getString("name"),
                              rs.getString("city"),
                              rs.getString("info"),
                              categoryList);
    }

    private List<Category> jsonCategoryConverter(JSONArray categoryArr) {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < categoryArr.length(); i++) {
            JSONObject category =  categoryArr.getJSONObject(i);

            String categoryId = category.getString("id");
            String categoryName = category.getString("name");
            JSONArray menus = category.getJSONArray("menuList");
            List<Menu> menuList = new ArrayList<>();
            for (int j = 0; j < menus.length(); j++) {
                JSONObject menu =  menus.getJSONObject(j);
                String menuId = menu.getString("id");
                String menuName = menu.getString("name");
                int menuPrice = menu.getInt("price");
                menuList.add(new Menu(menuId, menuName, menuPrice));
            }
            categoryList.add(new Category(categoryId, categoryName, menuList));
        }
        return categoryList;
    }
}
