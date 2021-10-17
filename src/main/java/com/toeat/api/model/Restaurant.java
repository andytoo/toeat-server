package com.toeat.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table("restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {
    @Id
    private UUID id;
    private String name;
    private String city;
    private String info;
    private List<Category> categoryList;
}