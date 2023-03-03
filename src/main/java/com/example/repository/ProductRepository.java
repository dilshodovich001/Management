package com.example.repository;

import com.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository  extends JpaRepository<ProductEntity,String > {
    @Transactional
    @Modifying
    @Query("update ProductEntity set productName = ?2 where id = ?1")
    int update(String id, String productName);

}
