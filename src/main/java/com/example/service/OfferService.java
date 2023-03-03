package com.example.service;

import com.example.dto.OfferDTO;
import com.example.entity.OfferEntity;
import com.example.entity.ProductEntity;
import com.example.entity.RegionEntity;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final RegionService regionService;
    private final ProductService productService;

    public String addOffer(OfferDTO dto) {
        RegionEntity region = regionService
                .getNameFind(dto.getPlaceName());
        boolean b = region.getName().trim().equalsIgnoreCase(dto.getPlaceName().trim());
        ProductEntity product = productService.get(dto.getProductId());
        if (!b && product == null) {
            log.info("Not found --> " + region + " " + product);
            throw new ItemNotFoundException("Place Name Or Product Not Found");
        }
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setPlaceName(dto.getPlaceName());
        offerEntity.setProductId(dto.getProductId());
        offerRepository.save(offerEntity);

        return "Successfully added Offer";
    }

    public OfferEntity get(String id) {
        return offerRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Not Found Offer")
                );
    }
}
