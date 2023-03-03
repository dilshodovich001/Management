package com.example.controller;

import com.example.dto.RegionDTO;
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
@RequestMapping("/region")
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/add_region")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RegionDTO> addRegion(@RequestBody @Valid  RegionDTO regionDTO){
        log.info("Add Region --> "+ regionDTO);
        RegionDTO response = regionService.addRegion(regionDTO);
        return ResponseEntity.ok(response);
   }

   @GetMapping("/region_list")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public ResponseEntity<List<RegionDTO>> regionList(){
        List<RegionDTO> response = regionService.regionList();
        return ResponseEntity.ok(response);
   }


    @PutMapping("region_update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RegionDTO> update(@PathVariable Integer id,
                                              @Valid @RequestBody RegionDTO dto) {
        log.info("Update Region --> " + id + " dto " + dto);
        RegionDTO response = regionService.update(id, dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("region_delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.info("Delete Region --> " + id);
        regionService.delete(id);
        return ResponseEntity.ok().build();
    }




}
