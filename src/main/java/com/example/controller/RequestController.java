package com.example.controller;

import com.example.dto.RequestDTO;
import com.example.service.RequestService;
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
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/add_request")
    public ResponseEntity<RequestDTO> addRegion(@RequestBody @Valid RequestDTO dto){
        log.info("Add Request --> "+ dto);
        RequestDTO response = requestService.addRequest(dto);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/request_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RequestDTO>> requestList() {
        List<RequestDTO> response = requestService.requestList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_request/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> update(@PathVariable String id,
                                           @Valid @RequestBody RequestDTO requestDTO) {
        log.info("Update Request --> " + id + " dto " + requestDTO);
        int response = requestService.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete_request/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("Delete Request --> " + id);
        requestService.delete(id);
        return ResponseEntity.ok().build();
    }

}
