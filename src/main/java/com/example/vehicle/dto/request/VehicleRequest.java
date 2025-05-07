package com.example.vehicle.dto.request;

import lombok.*;

import java.time.Instant;

@Data
public class VehicleRequest {
    private String name;
    private Integer year;
    private Double price;
    private String owner;
    private Instant createdAt;
    private Long brandId;
}