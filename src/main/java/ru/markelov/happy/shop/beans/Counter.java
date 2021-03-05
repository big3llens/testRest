package ru.markelov.happy.shop.beans;

import lombok.Data;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Data
public class Counter {
    private static int addProductToCart = 0;
    private static int deleteProductToCart = 0;
    private static int clearCart = 0;
    private static int createOrder = 0;
    private static int showUserOrders = 0;
    private static int findAllProducts = 0;
    private static int findProductById = 0;
    private static int createProduct = 0;
    private static int updateProduct = 0;
    private static int deleteById = 0;
    private static int deleteAllProducts = 0;


    @After("execution(public * ru.markelov.happy.shop.controllers.CartController.addProductToCart(..))")
    public static void addProductToCart() {
        addProductToCart += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.CartController.deleteProductToCart(..))")
    public static void deleteProductToCart() {
        deleteProductToCart += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.CartController.clearCart(..))")
    public static void clearCart() {
        clearCart += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.OrderController.createOrder(..))")
    public static void createOrder() {
        createOrder += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.OrderController.showUserOrders(..))")
    public static void showUserOrders() {
        showUserOrders += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.findAllProducts(..))")
    public static void findAllProducts() {
        findAllProducts += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.findProductById(..))")
    public static void findProductById() {
        findProductById += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.createProduct(..))")
    public static void createProduct() {
        createProduct += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.updateProduct(..))")
    public static void updateProduct() {
        updateProduct += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.deleteById(..))")
    public static void deleteById() {
        deleteById += 1;
    }

    @After("execution(public * ru.markelov.happy.shop.controllers.ProductController.deleteAllProducts(..))")
    public static void deleteAllProducts() {
        deleteAllProducts += 1;
    }

    public static int getAddProductToCart() {
        return addProductToCart;
    }

    public static int getDeleteProductToCart() {
        return deleteProductToCart;
    }

    public static int getClearCart() {
        return clearCart;
    }

    public static int getCreateOrder() {
        return createOrder;
    }

    public static int getShowUserOrders() {
        return showUserOrders;
    }

    public static int getFindAllProducts() {
        return findAllProducts;
    }

    public static int getFindProductById() {
        return findProductById;
    }

    public static int getCreateProduct() {
        return createProduct;
    }

    public static int getUpdateProduct() {
        return updateProduct;
    }

    public static int getDeleteById() {
        return deleteById;
    }

    public static int getDeleteAllProducts() {
        return deleteAllProducts;
    }
}


