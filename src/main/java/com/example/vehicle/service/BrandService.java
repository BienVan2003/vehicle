package com.example.vehicle.service;

import com.example.vehicle.dto.BrandDTO;
import com.example.vehicle.entity.Brand;
import com.example.vehicle.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    // Lấy tất cả các hãng xe
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // Lấy hãng xe theo ID
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    // Tạo mới một hãng xe
    public Brand createBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setType(brandDTO.getType());
        return brandRepository.save(brand);
    }

    // Cập nhật hãng xe
    public Brand updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        brand.setName(brandDTO.getName());
        brand.setType(brandDTO.getType());
        return brandRepository.save(brand);
    }

    // Xóa hãng xe
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
