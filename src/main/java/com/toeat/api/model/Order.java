package com.toeat.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Table("orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private UUID id;
    private String phone;
    private UUID restaurantId;
    private List<Item> itemList;
    private int total;
    private Timestamp date;
}
