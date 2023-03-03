package com.example.repository;

import com.example.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends JpaRepository<RequestEntity , String> {
    RequestEntity findByProductIdAndDistrictId(String productId , Integer placeNameId);


    @Transactional
    @Modifying
    @Query("update RequestEntity r set " +
            "r.placeName = ?2, " +
            "r.productId = ?3 ," +
            "r.districtId = ?4 where r.id = ?1")
    int update(String id, String placeName, String productId, Integer districtId);
}
