package ru.markelov.happy.shop.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.markelov.happy.shop.dto.ProductDto;
import ru.markelov.happy.shop.models.OrderItem;
import ru.markelov.happy.shop.models.Product;
import ru.markelov.happy.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class ProductService {
    private ProductRepository productRepository;
    private List<OrderItem> productCart;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
        productCart = new ArrayList<>();
    }

//    public List<ProductDto> findAllProducts(){
//        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
//    }

    public Page<ProductDto> findAllProducts(Integer page){
        return productRepository.findAll(PageRequest.of(page - 1, 5)).map(ProductDto::new);
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public ProductDto findProductDtoById(Long id){
        ProductDto productDto = new ProductDto(productRepository.findById(id).get());
        return productDto;
    }

    public void createProduct(ProductDto p){
        Product product = new Product(p.getTitle(), p.getCost());
        productRepository.save(product);
    }

    public void updateProduct(ProductDto productDto){
        Product product = productRepository.getOne(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void deleteAllProducts(){
        productRepository.deleteAll();
    }

//    public List<OrderItem> addProductToCart(String title, Integer cost){
//        if(productCart.isEmpty()) {
//            productCart.add(new OrderItem(title, 1, cost));
//            return productCart;
//        }
//        for(int i = 0; i <productCart.size(); i ++){
//           if(title.equals(productCart.get(i).getTitle())) {
//               productCart.get(i).setCount(productCart.get(i).getCount() + 1);
//               productCart.get(i).setCost(productCart.get(i).getCost() + cost);
//               return productCart;
//           }
//        }
//        productCart.add(new OrderItem(title, 1, cost));
//        return productCart;
//    }
}
