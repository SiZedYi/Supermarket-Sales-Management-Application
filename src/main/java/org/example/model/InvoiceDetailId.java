package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class InvoiceDetailId implements Serializable {
    @Column(name = "invoiceid")
    private Long invoiceId;

    @Column(name = "productid")
    private Long productId;

    // constructors, getters, setters, hashCode, equals
}
