package com.example.vehicle.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
public class VehicleDTO {
    private String name;
    private Integer year;
    private Double price;
    private String owner;
    private Instant createdAt;
    private Long brandId;
}