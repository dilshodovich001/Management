package com.example.controller;


import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import com.example.util.SpringSecurityUtil;
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
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/add_profile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        log.info("Add Profile --> " + profileDTO);
        String response = profileService.addProfile(profileDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> profileList() {
        List<ProfileDTO> response = profileService.profileList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_profile/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProfileDTO> update(@PathVariable Integer id,
                                         @Valid @RequestBody ProfileDTO profileDTO) {
        log.info("Update Profile --> " + id + " dto " + profileDTO);
        ProfileDTO response = profileService.updateAdmin(id, profileDTO);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete_profile/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.info("Delete Profile --> " + id);
        profileService.delete(id);
        return ResponseEntity.ok().build();
    }

}
