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
public class OfferDTO {

    protected String id;

    @Size(min = 4,message = "Place Name is required")
    private String placeName;

    @Size(min = 4,message = "Product Id is required")
    private String productId;

    private ProductDTO product;

}
