package ru.markelov.happy.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.markelov.happy.shop.beans.Cart;
import ru.markelov.happy.shop.models.Order;
import ru.markelov.happy.shop.models.User;
import ru.markelov.happy.shop.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createOrder (User user, String city, String street, String numberOfHouse){
        Order order = new Order(cart, user, city, street, numberOfHouse);
        order = orderRepository.save(order);
        cart.clear();
        return order;
//        System.out.println("2. " + order.getItems());
    }

    public List<Order> showUserOrders(String username){
        return orderRepository.findAllByUserUsername(username);
    }
}
