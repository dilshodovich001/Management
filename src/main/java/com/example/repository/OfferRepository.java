package com.example.repository;

import com.example.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OfferRepository
extends JpaRepository<OfferEntity,String> {

    @Transactional
    @Modifying
    @Query("update OfferEntity o set o.placeName = ?2 , o.productId = ?3 where o.id = ?1")
    void updateById(String id, String placeName, String productId);
}
