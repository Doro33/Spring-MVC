package com.example.minibank.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String hashedPassword;
    private BigDecimal balance;

    @PrePersist
    public void init() {
        if (balance == null)
            balance = BigDecimal.ZERO;
    }

    public Client(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.balance = BigDecimal.ZERO;
    }
}
