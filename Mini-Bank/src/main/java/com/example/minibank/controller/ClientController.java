package com.example.minibank.controller;

import com.example.minibank.dto.ClientDto;
import com.example.minibank.dto.MoneyExchangeDto;
import com.example.minibank.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public String clientSignUp(@Valid @RequestBody ClientDto clientDto) throws InvalidNameException {
        service.signUp(clientDto);
        return "Your account has been signed up successfully!";
    }

    @PostMapping("/deposit")
    public String deposit(@Valid @RequestBody MoneyExchangeDto moneyExchangeDto) throws InvalidNameException {
        BigDecimal newBalance =  service.deposit(moneyExchangeDto);
        return "Your balance has been deposited successfully!\n"+
                 "Balance: " +newBalance;
    }

    @PostMapping("/withdraw")
    public String withdraw(@Valid @RequestBody MoneyExchangeDto moneyExchangeDto) throws InvalidNameException {
        BigDecimal newBalance = service.withdraw(moneyExchangeDto);
        return "Your balance has been withdraw successfully!\n"+
                "Balance: " +newBalance;
    }

}
