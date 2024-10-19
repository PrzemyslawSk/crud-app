package com.przemyslawsk.crudapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, name = "name")
    private String name;
    @Column(nullable = false, length = 50, name = "description")
    private String description;
    @Column(nullable = false, name = "price")
    private BigDecimal price;
    @Column(nullable = false, name = "date_added")
    private LocalDateTime dateAdded;
}
