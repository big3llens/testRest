package ru.markelov.happy.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.markelov.happy.shop.models.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int cost;

    public ProductDto(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public ProductDto(Product p){
        this.id = p.getId();
        this.cost = p.getCost();
        this.title = p.getTitle();
    }

}
