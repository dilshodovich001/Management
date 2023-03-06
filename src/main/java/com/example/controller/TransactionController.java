package com.example.controller;

import com.example.dto.EvaluateDTO;
import com.example.dto.TransactionDTO;
import com.example.mapper.TransactionMapper;
import com.example.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/evaluate_transaction")
    public ResponseEntity<Boolean> evaluateTransaction(
           @RequestBody EvaluateDTO dto
    ){
        log.info("Evaluate Transaction --> "+ dto);
        Boolean response = transactionService
                .evaluateTransaction(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TransactionMapper>> getList(){
        List<TransactionMapper> response = transactionService.getList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.info("Delete Transaction --> " + id);
        transactionService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(){
        return ResponseEntity.ok().build();
    }
}
