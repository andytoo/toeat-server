package com.toeat.api.model;//package com.toeat.api.model;
//
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//import lombok.*;
//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "restaurants")
//@Data
//@NoArgsConstructor
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//public class Restaurant implements Serializable {
//
//    @Id
////    @Type(type="org.hibernate.type.PostgresUUIDType")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//    private String name;
//    private String city;
//    private String info;
//
//    @Type(type = "jsonb")
//    @Column(name = "menu", columnDefinition = "jsonb")
//    private List<Category> category;
//
////    @Getter(AccessLevel.NONE)
////    @Setter(AccessLevel.NONE)
////    @OneToMany
////    @JoinColumn(name="order_id", referencedColumnName = "id")
////    private List<Order> orders;
//
////    @OneToOne
////    private Customer customer;
//
//    public Restaurant(String name, String city, String info) {
//        this.name = name;
//        this.city = city;
//        this.info = info;
//    }
//}
