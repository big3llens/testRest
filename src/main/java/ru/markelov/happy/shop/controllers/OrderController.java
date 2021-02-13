package ru.markelov.happy.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

//    @PathVariable String city, @PathVariable String street, @PathVariable String numberOfHouse,   {city}/{street}/{numberOfHouse}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal,  @RequestParam("city") String city, @RequestParam("street") String street, @RequestParam("numberOfHouse") String numberOfHouse){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        orderService.createOrder(user, city, street, numberOfHouse);
//        System.out.println("Flhtccccccc: " + city + " " + street + " " + numberOfHouse);
    }

    @GetMapping("/show")
    public List<OrderDto> showUserOrders(Principal principal){
        return orderService.showUserOrders(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
