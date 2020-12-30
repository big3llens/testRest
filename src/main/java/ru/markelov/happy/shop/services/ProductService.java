package ru.markelov.happy.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.markelov.happy.shop.models.Product;
import ru.markelov.happy.shop.repositories.ProductRepository;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
