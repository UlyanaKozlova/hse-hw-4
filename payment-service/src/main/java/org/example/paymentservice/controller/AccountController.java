package org.example.paymentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.paymentservice.dto.account.AccountResponse;
import org.example.paymentservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payment-service")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @Operation(description = "Add new account by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Account for this userId already exists")
    })
    @PostMapping("/{userId}/account")
    public ResponseEntity<AccountResponse> addAccount(@Parameter(description = "User id, each userId corresponds to one account", example = "15")
                                                      @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(userId));
    }

    @Operation(description = "Increase account balance by account id and sum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No account by id is found")
    })
    @PatchMapping("/{id}/top-up/{sum}/account")
    public ResponseEntity<AccountResponse> topUpAccount(@Parameter(description = "Account id", example = "1")
                                                        @PathVariable Long id,
                                                        @Parameter(description = "Sum by which the balance will be increased", example = "100")
                                                        @PathVariable Long sum) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.topUpAccount(id, sum));
    }

    @Operation(description = "Get account balance by account id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No account by account id is found")
    })
    @GetMapping("/{id}/balance")
    public ResponseEntity<Long> getBalance(@Parameter(description = "Account id", example = "1")
                                           @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getBalance(id));
    }

    @Operation(description = "Get all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Account repository is empty")
    })
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }
}
