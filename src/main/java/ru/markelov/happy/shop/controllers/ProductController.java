package ru.markelov.happy.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.markelov.happy.shop.dto.ProductDto;
import ru.markelov.happy.shop.models.CartsElement;
import ru.markelov.happy.shop.models.Product;
import ru.markelov.happy.shop.repositories.ProductRepository;
import ru.markelov.happy.shop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> findAllProducts (
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ){
        return productService.findAllProducts(page);
    }

    @GetMapping("/cart")
    public List<CartsElement> addProductToCart(@RequestParam String title, @RequestParam Integer cost){
        return productService.addProductToCart(title, cost);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto){
        productService.createProduct(productDto);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto){
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllProducts(){
        productService.deleteAllProducts();
    }

}
