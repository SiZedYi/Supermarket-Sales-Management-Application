package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "userid")
    private String userId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;
}