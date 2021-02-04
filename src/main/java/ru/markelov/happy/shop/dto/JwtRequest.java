package ru.markelov.happy.shop.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
