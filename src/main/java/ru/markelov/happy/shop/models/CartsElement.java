package ru.markelov.happy.shop.models;

import lombok.Data;

@Data
public class CartsElement {
    private String title;
    private Integer count;
    private Integer cost;

    public CartsElement(String title, int count, int cost) {
        this.title = title;
        this.count = count;
        this.cost = cost;
    }
}
