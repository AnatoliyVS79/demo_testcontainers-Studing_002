package com.example.demo_testcontainers.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "u_login")
    String login;

    @Column(name = "email")
    String email;

}
