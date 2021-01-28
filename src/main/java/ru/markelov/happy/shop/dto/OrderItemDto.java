package ru.markelov.happy.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.markelov.happy.shop.models.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private String title;
    private int cost;
    private int totalCost;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        this.title = orderItem.getTitle();
        this.cost = orderItem.getCost();
        this.totalCost = orderItem.getTotalCost();
        this.count = orderItem.getCount();
    }
}
