package com.example.vehicle.mapper;

import com.example.vehicle.dto.request.BrandRequest;
import com.example.vehicle.dto.response.BrandResponse;
import com.example.vehicle.entity.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toResponse(Brand brand);
    List<BrandResponse> toResponse(List<Brand> brand);
    Brand toEntity(BrandRequest brandRequest);
}


