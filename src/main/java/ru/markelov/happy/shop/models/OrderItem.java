package ru.markelov.happy.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "count")
    private Integer count;

    @Column(name = "totalCost")
    private Integer totalCost;

    private String title;

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
