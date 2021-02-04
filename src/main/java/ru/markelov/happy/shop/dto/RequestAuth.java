package ru.markelov.happy.shop.dto;

import lombok.Data;

@Data
public class RequestAuth {
    private String token;
    private String name;

    public RequestAuth(String token, String name) {
        this.token = token;
        this.name = name;
    }
}
