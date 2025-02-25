package com.cmed.assesment_03.entity.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email(message = "Provide a valid Email address")
    @Column(nullable = false,unique = true,updatable = false)
    private String email;

    @Column(updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
