package com.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer ball;
    @Column(name = "carrier_name")
    private Integer carrierId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_name", insertable = false, updatable = false)
    private ProfileEntity carrier;

    @Column(name = "request_id")
    private String requestId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private RequestEntity request;

    @Column(name = "offer_id")
    private String offerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", insertable = false, updatable = false)
    private OfferEntity offer;

}
