package com.example.vehicle.dto.response;

import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleResponse {
    Long id;
    String name;
    Integer year;
    Double price;
    String owner;
    Instant createdAt;
    BrandResponse brand;
}
