package com.example.service;

import com.example.dto.ProductDTO;
import com.example.dto.RegionDTO;
import com.example.entity.ProductEntity;
import com.example.entity.RegionEntity;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.ProductRepository;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDTO addProduct(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setProductName(dto.getProductName());
        productRepository.save(entity);
        return dto;
    }

    public List<ProductDTO> productList() {
        List<ProductEntity> all = productRepository.findAll();
        if (all.isEmpty()) {
            throw new ItemNotFoundException("Not Found");
        }
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity productEntity : all) {
            ProductDTO dto = new ProductDTO();
            dto.setId(productEntity.getId());
            dto.setProductName(productEntity.getProductName());
            productDTOS.add(dto);
        }
        return productDTOS;
    }

    public int update(String id, ProductDTO dto) {
        get(id);
        return productRepository.update(id, dto.getProductName());
    }

    public ProductEntity get(String id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Not found Product"));
    }

    public void delete(String id) {
        get(id);
        productRepository.deleteById(id);
    }


}
