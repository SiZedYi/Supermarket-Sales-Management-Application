package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Account")
public class Account implements Serializable {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(nullable = false)
    private String password;
}