package com.example.service;

import com.example.dto.RegionDTO;
import com.example.entity.ProfileEntity;
import com.example.entity.RegionEntity;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionDTO addRegion(RegionDTO regionDTO) {
        Optional<RegionEntity> region = regionRepository.findByNameIgnoreCase(regionDTO.getName());
        if (region.isPresent()) {
            throw new ItemAlreadyExistException("already there");
        }
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setName(regionDTO.getName().toLowerCase());
        regionRepository.save(regionEntity);

        return regionDTO;
    }

    public List<RegionDTO> regionList() {
        List<RegionEntity> all = regionRepository.findAllByOrderByName();
        List<RegionDTO> regionDTOS = new ArrayList<>();
        for (RegionEntity regionEntity : all) {
        RegionDTO dto = new RegionDTO();
        dto.setName(regionEntity.getName());
        dto.setId(regionEntity.getId());
        regionDTOS.add(dto);
        }
        return regionDTOS;
    }

    public RegionEntity get(Integer id) {
        return regionRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Region Not Found"));
    }
    public RegionEntity getNameFind(String name) {
        return regionRepository.findByName(name).
                orElseThrow(() -> new ItemNotFoundException
                        ("Region Name Not Found"));
    }

    public RegionDTO update(Integer id, RegionDTO dto) {
        get(id);
        regionRepository.update(id,dto.getName());
        return dto;
    }

    public void delete(Integer id) {
        get(id);
        regionRepository.deleteById(id);
    }
}
