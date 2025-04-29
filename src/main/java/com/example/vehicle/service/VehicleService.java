package com.example.vehicle.service;

import com.example.vehicle.dto.*;
import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.repository.BrandRepository;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDTO.getName());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setCreatedAt(Instant.now());

         Brand brand = brandRepository.findById(vehicleDTO.getBrandId()).orElseThrow();
         vehicle.setBrand(brand);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        vehicle.setName(vehicleDTO.getName());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setCreatedAt(vehicleDTO.getCreatedAt());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> searchVehicles(VehicleSearchDTO req) {
        return vehicleRepository.searchVehicles(req);
    }

    public List<Vehicle> getCustomVehicles() {
        Double priceThreshold = 10_000_000.0;
        String brandPrefix = "S";
        String brandType = "BUS";

        return vehicleRepository.findVehiclesByCustomCondition(priceThreshold, brandPrefix, brandType);
    }
}
