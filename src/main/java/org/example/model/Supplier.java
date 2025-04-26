package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Suppliers")
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private String supplierId;

    private String companyName;
    private String address;
    private String city;
    private String country;

    @Override
    public String toString() {
        return  companyName;
    }
}