package com.example.vehicle.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleSearchRequest {
    String brandName;
    Integer year;
    Double price;
    String owner;
    @Min(0)
    Integer page = 0;
    @Min(1)
    Integer size = 10;
}
