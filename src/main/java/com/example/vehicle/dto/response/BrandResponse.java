package com.example.vehicle.dto.response;

import com.example.vehicle.entity.Vehicle;
import lombok.Data;

import java.util.List;

@Data
public class BrandResponse {
    private Long id;
    private String name;
    private String type;
}
