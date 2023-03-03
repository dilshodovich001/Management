package com.example.repository;

import com.example.entity.OfferEntity;
import com.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfferRepository
extends JpaRepository<OfferEntity,String> {

}
