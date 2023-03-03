package com.example.repository;

import com.example.entity.CarrierRegionPlaceEntity;
import com.example.mapper.CarrierRegionMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface CarrierRegionPlaceRepository
        extends JpaRepository<CarrierRegionPlaceEntity, Integer> {
    Optional<CarrierRegionPlaceEntity> findByProfileIdAndRegionId
            (Integer profileId, Integer regionId);

    @Query("select new com.example.mapper.CarrierRegionMapper" +
            "(c.profile.name,c.region.name) " +
            "from CarrierRegionPlaceEntity c order by  c.region.name , c.profile.name")
    List<CarrierRegionMapper> carrierRegionList();

    @Query("select distinct new com.example.mapper.CarrierRegionMapper(c.profile.name) from " +
            "CarrierRegionPlaceEntity c order by c.profile.name")
    List<CarrierRegionMapper> carrierNameList(String regionName);


    @Transactional
    @Modifying
    @Query("update CarrierRegionPlaceEntity c set c.profile.id = ?2 where c.id = ?1")
    Boolean update(Integer id, Integer carrierId);
}
