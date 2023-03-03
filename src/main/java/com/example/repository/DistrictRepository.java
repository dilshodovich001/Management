package com.example.repository;

import com.example.dto.DistrictDTO;
import com.example.entity.DistrictEntity;
import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<DistrictEntity,Integer> {

    Optional<DistrictEntity> findByNameAndRegionId(String name,Integer id);

    List<DistrictEntity> findAllByOrderByRegionId();

    @Transactional
    @Modifying
    @Query("update DistrictEntity set name = ?2 where id = ?1")
    void update(Integer id, String name);
}
