package com.pratice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    @Column(length = 40)
    private String name;
    private double price;
    private Integer qty;
    private LocalDateTime importedDate;
    private Boolean status;
}
