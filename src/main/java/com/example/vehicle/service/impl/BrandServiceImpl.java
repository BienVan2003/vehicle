package com.example.vehicle.service.impl;

import com.example.vehicle.constant.MessageCode;
import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.BrandRequest;
import com.example.vehicle.dto.response.BrandResponse;
import com.example.vehicle.entity.Brand;
import com.example.vehicle.mapper.BrandMapper;
import com.example.vehicle.repository.BrandRepository;
import com.example.vehicle.service.BrandService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;
    BrandMapper brandMapper;

    public ResDTO<?> getBrands() {
        List<BrandResponse> brands = brandMapper.toResponse(brandRepository.findAll());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.BRAND_FETCHED, brands);
    }

    @Override
    public ResDTO<?> getDeletedBrands() {
        List<BrandResponse> brands = brandMapper.toResponse(brandRepository.findByIsDeletedTrue());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.BRAND_FETCHED, brands);
    }

    @Override
    public ResDTO<?> getActiveBrands() {
        List<BrandResponse> brands = brandMapper.toResponse(brandRepository.findByIsDeletedFalseOrIsDeletedIsNull());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.BRAND_FETCHED, brands);
    }

    public ResDTO<?> getBrandById(Long id) {
        Brand brand = brandRepository.findActiveById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + id));
        BrandResponse brandRes = brandMapper.toResponse(brand);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.BRAND_FETCHED, brandRes);
    }

    public ResDTO<?> createBrand(BrandRequest brandDTO) {
        Brand brand = Brand.builder()
                .name(brandDTO.getName())
                .type(brandDTO.getType())
                .isDeleted(false)
                .build();

        Brand saveBrand = brandRepository.save(brand);

        BrandResponse brandRes = brandMapper.toResponse(saveBrand);
        return new ResDTO<>(HttpServletResponse.SC_CREATED, MessageCode.BRAND_CREATED_SUCCESS, brandRes);
    }

    public ResDTO<?> updateBrand(Long id, BrandRequest brandDTO) {
        Brand brand = brandRepository.findActiveById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + id));
        brand.setName(brandDTO.getName());
        brand.setType(brandDTO.getType());
        Brand updateBrand = brandRepository.save(brand);

        BrandResponse brandRes = brandMapper.toResponse(updateBrand);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.BRAND_UPDATED_SUCCESS, brandRes);
    }

    public ResDTO<?> deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + id));
        brand.setIsDeleted(true);
        brandRepository.save(brand);
        return new ResDTO<>(HttpServletResponse.SC_NO_CONTENT, MessageCode.BRAND_DELETED_SUCCESS, null);
    }
}

