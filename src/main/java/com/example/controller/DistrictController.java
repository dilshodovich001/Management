package com.example.controller;

import com.example.dto.DistrictDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegionDTO;
import com.example.service.DistrictService;
import com.example.service.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {
    private final DistrictService districtService;

    @PostMapping("/add_district")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DistrictDTO> addDistrict(@Valid @RequestBody DistrictDTO dto) {
        log.info("Add District --> " + dto);
        DistrictDTO response = districtService.addDistrict(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/district_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DistrictDTO>> districtList() {
        List<DistrictDTO> response = districtService.districtList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("district_update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DistrictDTO> update(@PathVariable Integer id,
                                              @Valid @RequestBody DistrictDTO dto) {
        log.info("Update District --> " + id + " dto " + dto);
        DistrictDTO response = districtService.updateAdmin(id, dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("district_delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.info("Delete District --> " + id);
        districtService.delete(id);
        return ResponseEntity.ok().build();
    }


}
