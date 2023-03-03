package com.example.mapper;

import com.example.dto.ProfileDTO;
import com.example.dto.RegionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrierRegionMapper {
    private String carrierName;
    private String regionName;

    public CarrierRegionMapper(String carrierName, String regionName) {
        this.carrierName = carrierName;
        this.regionName = regionName;
    }

    public CarrierRegionMapper(String carrierName) {
        this.carrierName = carrierName;
    }

    private ProfileDTO profile;
    private RegionDTO region;
}
