package ru.markelov.happy.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "count")
    private Integer count;

    @Column(name = "totalCost")
    private Integer totalCost;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "title")
    private String title;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        setOrder(order, true);
    }

    void setOrder(Order order, boolean add) {
        this.order = order;
        if (order != null && add) {
            order.add(this, false);
        }
    }

    public OrderItem(Product product) {
        this.title = product.getTitle();
        this.id = product.getId();
        this.cost = product.getCost();
        this.count = 1;
        totalCost = product.getCost();
    }

    public void increment(){
        count ++;
        totalCost = count * cost;
    }

    public void decrement(){
        this.count --;
        totalCost = count * cost;
    }

}
