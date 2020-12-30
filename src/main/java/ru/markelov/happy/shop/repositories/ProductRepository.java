package ru.markelov.happy.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.markelov.happy.shop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
