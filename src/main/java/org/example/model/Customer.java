package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private String customerId;

    private String contactName;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String phone;

    public Customer(String contactName, String address, String city, String region, String phone) {
        this.contactName = contactName;
        this.address = address;
        this.city = city;
        this.region = region;
        this.phone = phone;
    }
}