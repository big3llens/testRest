package ru.markelov.happy.shop.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.markelov.happy.shop.beans.Cart;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private int price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void add(OrderItem orderItem) {
        getItems().add(orderItem);
        orderItem.setOrder(this);
    }

    public void remove(OrderItem orderItem) {
        getItems().remove(orderItem);
        orderItem.setOrder(null);
    }

    public Order (Cart cart, User user){
        this.items = new ArrayList<>();
        this.price = cart.getTotalPrice();
        this.user = user;
        cart.getItems().stream().forEach((oi) -> {
//            oi.setOrder(this);
//            items.add(oi);
            add(oi);
        });
//        System.out.println("1. " + this.items);
    }
}
