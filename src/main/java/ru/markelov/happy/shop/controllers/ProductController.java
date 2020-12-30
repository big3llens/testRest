package ru.markelov.happy.shop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.markelov.happy.shop.models.Product;
import ru.markelov.happy.shop.repositories.ProductRepository;
import ru.markelov.happy.shop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
