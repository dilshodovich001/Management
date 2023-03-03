package com.example.service;

import com.example.dto.DistrictDTO;
import com.example.dto.RegionDTO;
import com.example.entity.DistrictEntity;
import com.example.entity.RegionEntity;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.DistrictRepository;
import com.example.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;

    private final RegionService regionService;

    public DistrictDTO addDistrict(DistrictDTO dto) {
        Optional<DistrictEntity> district = districtRepository.findByNameAndRegionId(dto.getName(), dto.getRegionId());
        if (district.isPresent()) {
            throw new ItemAlreadyExistException("already there");
        }
        DistrictEntity districtEntity = new DistrictEntity();
        districtEntity.setName(dto.getName());
        districtEntity.setRegionId(dto.getRegionId());

        districtRepository.save(districtEntity);
        return dto;
    }

    public List<DistrictDTO> districtList() {
        List<DistrictEntity> all = districtRepository.findAllByOrderByRegionId();
        if (all.isEmpty()) {
            log.info("Not Found District list");
            throw new ItemNotFoundException("Not Found");
        }
        List<DistrictDTO> districtDTOS = new ArrayList<>();
        for (DistrictEntity districtEntity : all) {
            DistrictDTO dto = new DistrictDTO();
            dto.setName(districtEntity.getName());
            dto.setId(districtEntity.getId());
            dto.setRegionId(districtEntity.getRegionId());
            dto.setRegionName(regionService.get(districtEntity.getRegionId()).getName());
            districtDTOS.add(dto);
        }
        return districtDTOS;
    }

    public DistrictDTO updateAdmin(Integer id, DistrictDTO dto) {
        get(id);
        districtRepository.update(id, dto.getName());
        return dto;
    }

    public DistrictEntity get(Integer id) {
        return districtRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Not found District"));
    }

    public void delete(Integer id) {
        get(id);
        districtRepository.deleteById(id);
    }
}
