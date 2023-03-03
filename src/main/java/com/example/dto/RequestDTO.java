package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO {
    private String id;
    @Size(min = 4,message = "Place name is required")
    private String placeName;
    @NonNull
    private Integer districtId;
    @NonNull
    private String productId;

}
