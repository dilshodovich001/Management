package com.example.controller;


import com.example.dto.CarrierDTO;
import com.example.dto.ProfileDTO;
import com.example.mapper.CarrierRegionMapper;
import com.example.service.CarrierService;
import com.example.service.ProfileService;
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
@RequestMapping("/carrier")
public class CarrierController {

    private final CarrierService carrierService;

    @PostMapping("/add_carrier")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addCarrier(@Valid @RequestBody CarrierDTO carrierDTO) {
        log.info("Add Carrier --> " + carrierDTO);
        String response = carrierService.addCarrier(carrierDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/carrier_region_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CarrierRegionMapper>> profileList() {
        List<CarrierRegionMapper> response = carrierService.carrierRegionList();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get_carrier_for_region/{name}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CarrierRegionMapper>> getCarriersForRegion(
            @PathVariable("name") String regionName) {
        log.info("Carrier name List--> " +regionName);
        List<CarrierRegionMapper> response = carrierService.getCarriersForRegion(regionName);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        carrierService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> update(@PathVariable Integer id,
                                          @RequestBody CarrierDTO dto){
        Boolean response = carrierService.update(id,dto);
        return ResponseEntity.ok(response);
    }




}
