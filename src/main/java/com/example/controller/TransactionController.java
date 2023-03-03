package com.example.controller;

import com.example.dto.TransactionDTO;
import com.example.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/add_transaction")
    public ResponseEntity<String> addTransaction(
            @Valid @RequestBody TransactionDTO dto
            ){
        log.info("Add Transaction --> " + dto);
        String response = transactionService.addTransaction(dto);
        return ResponseEntity.ok(response);
    }
}
