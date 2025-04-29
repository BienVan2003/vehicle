package com.example.vehicle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleSearchDTO {
    private String brandName;
    private Integer year;
    private Double price;
    private String owner;
}
