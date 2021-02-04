package ru.markelov.happy.shop.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.markelov.happy.shop.exceptions.ResourceNotFoundException;
import ru.markelov.happy.shop.models.OrderItem;
import ru.markelov.happy.shop.models.Product;
import ru.markelov.happy.shop.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private Integer totalPrice;

    @PostConstruct
    private void init(){
        items = new ArrayList<>();
        totalPrice = 0;
    }

    public void addToCart(Long id){
        for (OrderItem oi: items) {
            if(oi.getId().equals(id)){
                oi.increment();
                incrementTotalCost(oi.getCost());
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        items.add(new OrderItem(p));
        incrementTotalCost(p.getCost());
        System.out.println("1" + items);
    }

    public void deleteProductToCart(Long id){
        for (OrderItem oi: items) {
            if(oi.getId().equals(id)){
                items.removeIf((o) -> o.getCount().equals(1));
                if(oi.getCount() >= 1){
                    oi.decrement();
                    decrementTotalCost(oi.getCost());
                }
                return;
            }
        }
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    public void incrementTotalCost(int cost) {
        totalPrice += cost;
    }

    public void  decrementTotalCost(int cost){
        totalPrice -= cost;
    }
}
