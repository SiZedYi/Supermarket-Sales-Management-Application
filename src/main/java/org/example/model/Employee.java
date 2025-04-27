package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Employee")
@ToString
public class Employee implements Serializable {
    @Id
    @Column(name = "userid")
    private String userId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;
}