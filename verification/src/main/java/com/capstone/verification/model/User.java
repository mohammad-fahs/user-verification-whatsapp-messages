package com.capstone.verification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.UUID;
@Entity()
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String number;
    public String password;
    public boolean verified;

    public User(String number, String password, boolean verified){
        this.number = number;
        this.password = password;
        this.verified = verified;
    }
}
