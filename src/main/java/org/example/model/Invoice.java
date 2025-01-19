package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceid")
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employeeid", nullable = false)
    private Employee employee;

    @Column(name = "orderDate")
    private Date orderDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;
}
