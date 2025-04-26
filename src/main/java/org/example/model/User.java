package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "User")
public class User implements Serializable {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "Name", nullable = false)
    private String hoTen;

    @Column(name = "ngaySinh")
    private String ngaySinh;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "gioiTinh")
    private String gioiTinh;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Account account;
}