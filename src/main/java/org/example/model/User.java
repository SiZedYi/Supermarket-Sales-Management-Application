package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name = "User")
public class User implements Serializable {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "Name", nullable = false)
    private String hoTen;

    @Column(name = "ngaySinh")
    private Date ngaySinh;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "gioiTinh")
    private String gioiTinh;

    @Column(name = "email", nullable = false)
    private String email;

    public User(String userId, String hoTen, Date ngaySinh, String cccd, String gioiTinh, String email) {
        this.userId = userId;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.gioiTinh = gioiTinh;
        this.email = email;
    }

    public User(String userId) {
        this.userId = userId;
    }
}