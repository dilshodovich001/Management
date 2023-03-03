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

import java.util.ArrayList;
import java.util.List;

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

    public List<OfferDTO> offerlist() {
        List<OfferDTO> dtos = new ArrayList<>();
        for (OfferEntity offerEntity : offerRepository.findAll()) {
            OfferDTO offerDto = new OfferDTO();
            offerDto.setProductId(offerEntity.getProductId());
            offerDto.setPlaceName(offerEntity.getPlaceName());
            offerDto.setId(offerEntity.getId());
            dtos.add(offerDto);
        }
        return dtos;
    }

    public OfferDTO update(String id, OfferDTO offerDTO) {
        get(id);
        productService.get(offerDTO.getProductId());
        offerRepository.updateById(id,offerDTO.getPlaceName(),offerDTO.getProductId());
        return offerDTO;
    }

    public void delete(String id) {
        get(id);
        offerRepository.deleteById(id);
    }
}
