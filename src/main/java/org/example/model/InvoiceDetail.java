package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "InvoiceDetails")
public class InvoiceDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceid", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    private Integer quantity;
    private Double unitPrice;
    private Double discount;
}