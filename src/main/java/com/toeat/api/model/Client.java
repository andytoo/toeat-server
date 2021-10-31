package com.toeat.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("clients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    private String phone;
    private String name;
    private String password;
    @Column("restaurantid")//TODO
    private UUID restaurantId;
}
