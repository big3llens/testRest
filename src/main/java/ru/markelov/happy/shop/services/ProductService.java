package ru.markelov.happy.shop.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.markelov.happy.shop.beans.Counter;
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

    public void countMethods(){
//        List<Integer> countMethodsArr = new ArrayList<>();
        Integer[] countMethodsArr = addCountMethods();
        Integer maxCount = 0;
        for (int i = 0; i < countMethodsArr.length; i++) {
            if(countMethodsArr[i] > maxCount) maxCount = countMethodsArr[i];
        }
        if(maxCount == countMethodsArr[0]) System.out.println("Чаще всего сработал метод: afterAddProductToCart() [количество раз: " + countMethodsArr[0] + "]");
        if(maxCount == countMethodsArr[1]) System.out.println("Чаще всего сработал метод: deleteProductToCart() [количество раз: " + countMethodsArr[1] + "]");
        if(maxCount == countMethodsArr[2]) System.out.println("Чаще всего сработал метод: clearCart() [количество раз: " + countMethodsArr[2] + "]");
        if(maxCount == countMethodsArr[3]) System.out.println("Чаще всего сработал метод: createOrder() [количество раз: " + countMethodsArr[3] + "]");
        if(maxCount == countMethodsArr[4]) System.out.println("Чаще всего сработал метод: showUserOrders() [количество раз: " + countMethodsArr[4] + "]");
        if(maxCount == countMethodsArr[5]) System.out.println("Чаще всего сработал метод: findAllProducts() [количество раз: " + countMethodsArr[5] + "]");
        if(maxCount == countMethodsArr[6]) System.out.println("Чаще всего сработал метод: findProductById() [количество раз: " + countMethodsArr[6] + "]");
        if(maxCount == countMethodsArr[7]) System.out.println("Чаще всего сработал метод: createProduct() [количество раз: " + countMethodsArr[7] + "]");
        if(maxCount == countMethodsArr[8]) System.out.println("Чаще всего сработал метод: updateProduct() [количество раз: " + countMethodsArr[8] + "]");
        if(maxCount == countMethodsArr[9]) System.out.println("Чаще всего сработал метод: deleteById() [количество раз: " + countMethodsArr[9] + "]");
        if(maxCount == countMethodsArr[10]) System.out.println("Чаще всего сработал метод: deleteAllProducts() [количество раз: " + countMethodsArr[10] + "]");


    }

    public Integer[] addCountMethods(){
        Integer[] countMethodsArr = new Integer[11];
        countMethodsArr[0] = Counter.getAddProductToCart();
        countMethodsArr[1] = Counter.getDeleteProductToCart();
        countMethodsArr[2] = Counter.getClearCart();
        countMethodsArr[3] = Counter.getCreateOrder();
        countMethodsArr[4] = Counter.getShowUserOrders();
        countMethodsArr[5] = Counter.getFindAllProducts();
        countMethodsArr[6] = Counter.getFindProductById();
        countMethodsArr[7] = Counter.getCreateProduct();
        countMethodsArr[8] = Counter.getUpdateProduct();
        countMethodsArr[9] = Counter.getDeleteById();
        countMethodsArr[10] = Counter.getDeleteAllProducts();
        return countMethodsArr;
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
