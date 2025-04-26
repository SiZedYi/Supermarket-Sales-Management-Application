package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
    @Id
    @Column(name = "userid")
    private String userId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;
}