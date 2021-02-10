package ru.markelov.happy.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markelov.happy.shop.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
