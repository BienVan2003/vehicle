package com.example.vehicle.controller;

import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.BrandRequest;
import com.example.vehicle.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAllBrands(@RequestParam(required = false) Boolean isDeleted) {
        ResDTO<?> res = (isDeleted == null)
                ? brandService.getBrands()
                : (isDeleted ? brandService.getDeletedBrands() : brandService.getActiveBrands());

        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        ResDTO<?> res = brandService.getBrandById(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandRequest brandDTO) {
        ResDTO<?> res = brandService.createBrand(brandDTO);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @RequestBody BrandRequest brandDTO) {
        ResDTO<?> res = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        ResDTO<?> res = brandService.deleteBrand(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
