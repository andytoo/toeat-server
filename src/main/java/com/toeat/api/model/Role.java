//package com.toeat.api.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//@Data
//@NoArgsConstructor
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//
//    @OneToMany
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Set<User> users = new HashSet<>();
//
//    public Role(String name) {
//        this.name = name;
//    }
//}
