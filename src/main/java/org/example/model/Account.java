package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Account")
@NoArgsConstructor
@ToString
public class Account implements Serializable {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(nullable = false)
    private String password;

    public Account(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}