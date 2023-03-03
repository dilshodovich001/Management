package com.example.service;

import com.example.dto.CarrierDTO;
import com.example.entity.CarrierRegionPlaceEntity;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.mapper.CarrierRegionMapper;
import com.example.repository.CarrierRegionPlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CarrierService {
    private final CarrierRegionPlaceRepository carrierRegionPlaceRepository;

    public String addCarrier(CarrierDTO carrierDTO) {
        Optional<CarrierRegionPlaceEntity> byId = carrierRegionPlaceRepository
                .findByProfileIdAndRegionId(carrierDTO.getCarrierId(), carrierDTO.getRegionId());
        if (byId.isPresent()) {
            throw new ItemAlreadyExistException("Already exists");
        }
        CarrierRegionPlaceEntity entity = new CarrierRegionPlaceEntity();
        entity.setProfileId(carrierDTO.getCarrierId());
        entity.setRegionId(carrierDTO.getRegionId());
        carrierRegionPlaceRepository.save(entity);
        return "Successfully";
    }

    public List<CarrierRegionMapper> carrierRegionList() {
        List<CarrierRegionMapper> all = carrierRegionPlaceRepository.carrierRegionList();
        if (all.isEmpty()) {
            log.info("List Empty");
            throw new ItemNotFoundException("Error");
        }
        return all;
    }

    public List<CarrierRegionMapper> getCarriersForRegion(String regionName) {
        if (carrierRegionPlaceRepository.carrierNameList(regionName).isEmpty()) {
            log.info("List Empty");
            throw new ItemNotFoundException("Error");
        }
        return carrierRegionPlaceRepository.carrierNameList(regionName);
    }
}
