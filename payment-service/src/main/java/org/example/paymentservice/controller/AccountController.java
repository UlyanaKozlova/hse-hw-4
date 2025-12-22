package org.example.paymentservice.controller;

import lombok.AllArgsConstructor;
import org.example.paymentservice.dto.AccountResponse;
import org.example.paymentservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payment-service") // todo payment
public class AccountController {
    AccountService accountService;

    @PostMapping("/{userId}")
    public ResponseEntity<AccountResponse> addAccount(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(userId));
    }

    @PatchMapping("/{id}/top-up/{sum}")
    public ResponseEntity<AccountResponse> topUpAccount(@PathVariable Long id, @PathVariable Long sum) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.topUpAccount(id, sum));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getBalance(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getBalance(id));
    }
}
