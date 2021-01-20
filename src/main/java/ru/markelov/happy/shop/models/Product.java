package ru.markelov.happy.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }
}
