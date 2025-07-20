package com.example.minibank.repository;

import com.example.minibank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long>{
     Client findByUsername(String username);
}