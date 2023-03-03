package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotBlank
    @Size(min = 3, message = "Name is required")
    private String name;

    @NotBlank
    @Size(min = 3, message = "Surname is required")
    private String surname;

    @NotBlank
    @Size(min = 4, message = "Phone is wrong")
    private String phone;

    @NotBlank
    @Size(min = 4, message = "Password is wrong")
    private String password;

    @NotBlank
    @Email
    private String email;


    private ProfileRole role;

    private ProfileStatus status;
    private LocalDateTime createdDate;
    private Boolean visible;
}
