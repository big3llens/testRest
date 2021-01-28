package ru.markelov.happy.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
    private Long id;
    private String title;
    private Integer count;
    private Integer cost;
    private Integer totalCost;

    public OrderItem(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.count = 1;
        this.cost = product.getCost();
        totalCost = cost;
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
