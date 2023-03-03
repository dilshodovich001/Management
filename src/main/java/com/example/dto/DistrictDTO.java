package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDTO {
    private Integer id;

    @NotBlank
    @Size(min = 4 , message = "District name is required")
    private String name;
    @NonNull
    private Integer regionId;

    private String regionName;

}
