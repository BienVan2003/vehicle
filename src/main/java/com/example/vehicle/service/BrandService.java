package com.example.vehicle.service;

import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.BrandRequest;

public interface BrandService {

    ResDTO<?> getBrands();
    ResDTO<?> getDeletedBrands();
    ResDTO<?> getActiveBrands();

    ResDTO<?> getBrandById(Long id);

    ResDTO<?> createBrand(BrandRequest brandDTO);

    ResDTO<?> updateBrand(Long id, BrandRequest brandDTO);

    ResDTO<?> deleteBrand(Long id) ;
}
