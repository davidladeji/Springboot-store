package com.codewithdave.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Best practice to use @Column tag to define relationship to db column
    // This way, these parameter names can change without needing to edit the db

    @Column(nullable = false, name= "name")
    private String name;

    @Column(nullable = false, name= "email")
    private String email;

    @Column(nullable = false, name= "password")
    private String password;
}
