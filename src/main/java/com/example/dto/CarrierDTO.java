package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrierDTO {
    @NonNull
    private Integer carrierId;
    @NonNull
    private Integer regionId;

    private  RegionDTO region;
    private ProfileDTO profile;
}
