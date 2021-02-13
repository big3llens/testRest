package ru.markelov.happy.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.markelov.happy.shop.models.Order;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String username;
    private int Price;
    private String creationDateTime;
    private String adress;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.Price = order.getPrice();
        this.adress = order.getAdress();
        this.creationDateTime = order.getCreatedAt().toString();
    }
}
