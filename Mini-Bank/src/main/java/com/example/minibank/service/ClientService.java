package com.example.minibank.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.minibank.dto.ClientDto;
import com.example.minibank.dto.MoneyExchangeDto;
import com.example.minibank.model.Client;
import com.example.minibank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;

@Service
public class ClientService {
    private final ClientRepository repository;


    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void signUp(ClientDto clientDto) throws InvalidNameException {
        if (findByUsername(clientDto.getUsername()) != null) {
            throw new InvalidNameException("Username already exists");
        }
        String hashedPassword = BCrypt.withDefaults().hashToString(12,clientDto.getPassword().toCharArray());
        repository.save(new Client(clientDto.getUsername(), hashedPassword));
    }

    public BigDecimal deposit(MoneyExchangeDto moneyExchangeDto) throws InvalidNameException {
        Client client = findByUsername(moneyExchangeDto.getUsername());
        if (client == null) {
            throw new InvalidNameException("Username not found");
        }
        if ( !BCrypt.verifyer().verify(moneyExchangeDto.getPassword().getBytes(), client.getHashedPassword().getBytes()).verified) {
            throw new InvalidParameterException("Password is incorrect");
        }
        client.setBalance(client.getBalance().add(moneyExchangeDto.getAmount()));
        repository.save(client);
        return client.getBalance();
    }

    public BigDecimal withdraw(MoneyExchangeDto moneyExchangeDto) throws InvalidNameException {
        Client client = findByUsername(moneyExchangeDto.getUsername());
        if (client == null) {
            throw new InvalidNameException("Username not found");
        }
        if (!BCrypt.verifyer().verify(moneyExchangeDto.getPassword().getBytes(), client.getHashedPassword().getBytes()).verified) {
            throw new InvalidParameterException("Password is incorrect");
        }
        if (client.getBalance().compareTo(moneyExchangeDto.getAmount()) < 0) {
            throw new InvalidParameterException("Not enough balance");
        }
        client.setBalance(client.getBalance().subtract(moneyExchangeDto.getAmount()));
        repository.save(client);
        return client.getBalance();
    }

    private Client findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
