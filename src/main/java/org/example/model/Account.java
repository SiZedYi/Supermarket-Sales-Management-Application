package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "userid")
    private String userId;

    @Column(nullable = false)
    private String password;
}