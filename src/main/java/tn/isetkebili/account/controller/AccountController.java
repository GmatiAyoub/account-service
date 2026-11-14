package tn.isetkebili.account.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.isetkebili.account.entity.Account;
import tn.isetkebili.account.entity.AccountStatus;
import tn.isetkebili.account.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@Valid @RequestBody Account account) {
        return service.create(account);
    }

    @GetMapping
    public List<Account> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> findByCustomer(@PathVariable Long customerId) {
        return service.findByCustomerId(customerId);
    }

    // Utilisation de @RequestBody pour lire le statut depuis le JSON (et non @RequestMapping)
    @PatchMapping("/{id}/status")
    public Account updateStatus(@PathVariable Long id, @RequestBody AccountStatus status) {
        return service.updateStatus(id, status);
    }
    @PostMapping("/{id}/debit")
    public void debit(@PathVariable Long id, @RequestBody java.math.BigDecimal amount) {
        service.debit(id, amount);
    }

    @PostMapping("/{id}/credit")
    public void credit(@PathVariable Long id, @RequestBody java.math.BigDecimal amount) {
        service.credit(id, amount);
    }
}