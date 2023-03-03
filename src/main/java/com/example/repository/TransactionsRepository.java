package com.example.repository;

import com.example.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionsRepository
extends JpaRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity> findByRequestIdAndOfferId(
            String requestId , String offerId
    );
}
