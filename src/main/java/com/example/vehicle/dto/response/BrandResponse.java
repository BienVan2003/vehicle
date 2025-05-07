package com.example.vehicle.dto.response;

import com.example.vehicle.entity.Vehicle;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandResponse {
    Long id;
    String name;
    String type;
}
