package com.example.vehicle.dto.response;

import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import lombok.Data;

import java.time.Instant;

@Data
public class VehicleResponse {
    private Long id;
    private String name;
    private Integer year;
    private Double price;
    private String owner;
    private Instant createdAt;
    private BrandResponse brand;

}
