package com.example.repository;

import com.example.entity.TransactionEntity;
import com.example.mapper.TransactionMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository
extends JpaRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity> findByRequestIdAndOfferId(
            String requestId , String offerId
    );


    @Transactional
    @Modifying
    @Query("update TransactionEntity t set t.bal = ?2 where t.id = ?1")
    void updateBal(Integer transactionId, Integer bal);


    @Query("select distinct new com.example.mapper.TransactionMapper(t.bal,t.carrier.name,t.offer.placeName,t.request.placeName) from TransactionEntity t order by t.bal")
    List<TransactionMapper> getList();

}
