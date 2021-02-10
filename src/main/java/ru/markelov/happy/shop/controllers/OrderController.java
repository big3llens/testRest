package ru.markelov.happy.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.markelov.happy.shop.dto.OrderDto;
import ru.markelov.happy.shop.exceptions.ResourceNotFoundException;
import ru.markelov.happy.shop.models.User;
import ru.markelov.happy.shop.services.OrderService;
import ru.markelov.happy.shop.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        orderService.createOrder(user);
    }

    @GetMapping("/show")
    public List<OrderDto> showUserOrders(Principal principal){
        return orderService.showUserOrders(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
