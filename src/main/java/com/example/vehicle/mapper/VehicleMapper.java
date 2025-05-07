package com.example.vehicle.mapper;

import com.example.vehicle.dto.request.VehicleRequest;
import com.example.vehicle.dto.response.VehicleResponse;
import com.example.vehicle.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleResponse toResponse(Vehicle vehicle);
    List<VehicleResponse> toResponse(List<Vehicle> vehicle);
}

