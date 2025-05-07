package com.example.vehicle.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleRequest {
    String name;
    Integer year;
    Double price;
    String owner;
    Instant createdAt;
    Long brandId;
}