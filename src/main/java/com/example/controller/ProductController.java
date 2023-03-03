package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add_product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody
                                                     ProductDTO dto){
        log.info("Add Product --> "+ dto);
      ProductDTO response = productService.addProduct(dto);
      return ResponseEntity.ok(response);
    }

    @GetMapping("/product_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductDTO>> productList() {
        List<ProductDTO> response = productService.productList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> update(@PathVariable("id") String id,
                                                     @Valid  @RequestBody ProductDTO dto){
        log.info("Update Product --> "+ dto + " id " + id);
        int response = productService.update(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete_product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id
                                          ){
        log.info("Delete Product --> "+ id);
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
