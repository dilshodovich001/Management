package com.example.mapper;

import com.example.dto.ProfileDTO;
import com.example.dto.RegionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionMapper {
    private Integer  bal;
    private String carrierName;
    private String placeName;
    private String location;
}
