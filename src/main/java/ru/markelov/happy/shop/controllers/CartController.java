package ru.markelov.happy.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.markelov.happy.shop.beans.Cart;
import ru.markelov.happy.shop.dto.CartDto;
import ru.markelov.happy.shop.models.OrderItem;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart(){
//        System.out.println("2" + cart);
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cart.addToCart(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductToCart(@PathVariable Long id){
        cart.deleteProductToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }
}
