package ru.markelov.happy.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

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
}
