package com.example.controller;

import com.example.dto.auth.AuthRegistrationDTO;
import com.example.dto.auth.AuthRequestDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody AuthRegistrationDTO dto){
        log.info("Registration --> " + dto);
        String response = authService.registration(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid  @RequestBody AuthRequestDTO dto){
        log.info("Authorization --> "+dto);
        AuthResponseDTO response  = authService.login(dto);
        return ResponseEntity.ok(response);
    }




}
