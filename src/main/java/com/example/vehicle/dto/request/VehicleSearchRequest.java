package com.example.vehicle.dto.request;

import lombok.Data;

@Data
public class VehicleSearchRequest {
    private String brandName;
    private Integer year;
    private Double price;
    private String owner;
}
