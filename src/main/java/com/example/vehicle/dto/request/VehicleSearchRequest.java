package com.example.vehicle.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleSearchRequest {
    String brandName;
    Integer year;
    Double price;
    String owner;
}
