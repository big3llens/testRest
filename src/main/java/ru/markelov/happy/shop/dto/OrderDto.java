package ru.markelov.happy.shop.dto;

import ru.markelov.happy.shop.models.Order;

public class OrderDto {
    private Long id;
    private String username;
    private int Price;
    private String creationDateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.Price = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
    }
}
