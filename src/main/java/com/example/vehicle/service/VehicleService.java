package com.example.vehicle.service;

import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.VehicleRequest;
import com.example.vehicle.dto.request.VehicleSearchRequest;

public interface VehicleService {

    ResDTO<?> getAllVehicles();

    ResDTO<?> getVehicleById(Long id);

    ResDTO<?> createVehicle(VehicleRequest vehicleDTO);

    ResDTO<?> updateVehicle(Long id, VehicleRequest vehicleDTO);

    ResDTO<?> deleteVehicle(Long id);

    ResDTO<?> searchVehicles(VehicleSearchRequest req);

    ResDTO<?> getCustomVehicles();
}
