package ru.markelov.happy.shop.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.markelov.happy.shop.beans.Cart;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "user")
    private String user;

    @Column(name = "cart")
    @OneToMany
    private List<OrderItem> itemList;
}
