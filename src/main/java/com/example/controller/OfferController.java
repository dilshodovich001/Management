package com.example.controller;

import com.example.dto.OfferDTO;
import com.example.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/add_offer")
    public ResponseEntity<String> addOffer(@Valid
                                           @RequestBody OfferDTO dto){
        log.info("Add Offer --> "+ dto);
        String response = offerService.addOffer(dto);
        return ResponseEntity.ok(response);
    }
}
