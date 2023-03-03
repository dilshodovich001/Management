package com.example.controller;

import com.example.dto.OfferDTO;
import com.example.service.OfferService;
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

    @GetMapping("/offer_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OfferDTO>> offerlist() {
        List<OfferDTO> response = offerService.offerlist();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_offer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OfferDTO> update(@PathVariable String id,
                                             @Valid @RequestBody OfferDTO offerDTO) {
        log.info("Update Offer --> " + id + " dto " + offerDTO);
        OfferDTO response = offerService.update(id, offerDTO);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete_offer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("Delete Offer --> " + id);
        offerService.delete(id);
        return ResponseEntity.ok().build();
    }

}
