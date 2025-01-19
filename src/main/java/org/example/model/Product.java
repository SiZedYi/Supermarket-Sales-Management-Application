package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productId;

    private String productName;
    private Double unitPrice;
    private Integer unitsInStock;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierid", nullable = false)
    private Supplier supplier;
}