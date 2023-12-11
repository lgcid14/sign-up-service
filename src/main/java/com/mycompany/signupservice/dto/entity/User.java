package com.mycompany.signupservice.dto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "lastLogin")
    private LocalDateTime lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "isActive")
    private boolean active = true;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        lastLogin = LocalDateTime.now();
    }

}
