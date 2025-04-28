package com.example.vehicle.service;

import com.example.vehicle.dto.VehicleDTO;
import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.repository.BrandRepository;
import com.example.vehicle.repository.VehicleRepository;
import com.example.vehicle.repository.VehicleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
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

    public List<Vehicle> searchVehicles(String brandName, Integer year, Double price, String owner) {
        Specification<Vehicle> spec = Specification.where(VehicleSpecification.hasBrand(brandName))
                .and(VehicleSpecification.hasYear(year))
                .and(VehicleSpecification.hasPrice(price))
                .and(VehicleSpecification.hasOwner(owner));

        return vehicleRepository.findAll(spec);
    }

    public List<Vehicle> getVehiclesByCustomCondition() {
        Specification<Vehicle> spec = VehicleSpecification.customPriceAndBrandCondition();
        return vehicleRepository.findAll(spec);
    }
}
