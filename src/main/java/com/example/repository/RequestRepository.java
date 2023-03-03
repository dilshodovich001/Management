package com.example.repository;

import com.example.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity , String> {
    RequestEntity findByProductIdAndDistrictId(String productId , Integer placeNameId);
}
