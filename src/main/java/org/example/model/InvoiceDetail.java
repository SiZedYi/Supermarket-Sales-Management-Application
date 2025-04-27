package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "InvoiceDetails")
public class InvoiceDetail implements Serializable {
    @EmbeddedId
    private InvoiceDetailId id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoiceid", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    private Integer quantity;
    private Double unitPrice;
    private Double discount;

    // constructors, getters, setters
}