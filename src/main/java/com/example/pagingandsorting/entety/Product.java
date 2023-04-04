package com.example.pagingandsorting.entety;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "product")
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")

    private long id;
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {}
}
