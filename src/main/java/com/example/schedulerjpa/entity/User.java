package com.example.schedulerjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 사용자 entity
@Entity
@Getter
@NoArgsConstructor
@Table(name = "user") // user 데이터베이스 연결
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 사용자 수정
    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
