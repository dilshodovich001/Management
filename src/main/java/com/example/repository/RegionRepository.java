package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity ,Integer> {

    Optional<RegionEntity> findByNameIgnoreCase(String name);

    Optional<RegionEntity> findByName(String name);

    List<RegionEntity> findAllByOrderByName();

    @Transactional
    @Modifying
    @Query("update RegionEntity set name = ?2 where id = ?1")
    void update(Integer id, String name);
}
