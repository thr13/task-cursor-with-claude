package com.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 회원 고유 번호

    @Column(nullable = false, unique = true, length = 100)
    private String email; // 회원 이메일

    @Column(nullable = false, length = 100)
    private String password; // 회원 비밀번호

    @Setter
    @Column(nullable = false, length = 100)
    private String name; // 회원 이름

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // 회원 권한

    protected Member() {}

    public Member(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

}
