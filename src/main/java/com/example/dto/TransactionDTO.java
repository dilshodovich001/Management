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
public class TransactionDTO {
    @NonNull
    private Integer id;
    @NonNull
    private Integer ball;
    @NonNull
    private Integer carrierId;
    @Size(min = 4,message = "Request id is required")
    private String requestId;
    @Size(min = 4,message = "Offer id is required")
    private String offerId;
    private CarrierDTO carrier;
    private RequestDTO request;
    private OfferDTO offer;
}
