package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.dto.RequestDTO;
import com.example.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/add_request")
    public ResponseEntity<RequestDTO> addRegion(@RequestBody @Valid RequestDTO dto){
        log.info("Add Request --> "+ dto);
        RequestDTO response = requestService.addRequest(dto);
        return ResponseEntity.ok(response);
    }
}
