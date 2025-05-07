package com.example.vehicle.service.impl;

import com.example.vehicle.constant.MessageCode;
import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.VehicleRequest;
import com.example.vehicle.dto.request.VehicleSearchRequest;
import com.example.vehicle.dto.response.VehicleResponse;
import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.mapper.VehicleMapper;
import com.example.vehicle.repository.BrandRepository;
import com.example.vehicle.repository.VehicleRepository;
import com.example.vehicle.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public ResDTO<?> getAllVehicles() {
        List<VehicleResponse> vehicles = vehicleMapper.toResponse(vehicleRepository.findAll());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicles);
    }

    public ResDTO<?> getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        VehicleResponse vehicleRes = vehicleMapper.toResponse(vehicle);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicleRes);
    }

    public ResDTO<?> createVehicle(VehicleRequest vehicleReq) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleReq.getName());
        vehicle.setYear(vehicleReq.getYear());
        vehicle.setPrice(vehicleReq.getPrice());
        vehicle.setOwner(vehicleReq.getOwner());
        vehicle.setCreatedAt(Instant.now());

        if (vehicleReq.getBrandId() != null) {
            Brand brand = brandRepository.findById(vehicleReq.getBrandId())
                    .orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + vehicleReq.getBrandId()));

            vehicle.setBrand(brand);
        }

        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        VehicleResponse vehicleResponse = vehicleMapper.toResponse(saveVehicle);
        return new ResDTO<>(HttpServletResponse.SC_CREATED, MessageCode.VEHICLE_CREATED_SUCCESS, vehicleResponse);
    }

    public ResDTO<?> updateVehicle(Long id, VehicleRequest vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicle.setName(vehicleDTO.getName());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setOwner(vehicleDTO.getOwner());
        vehicle.setCreatedAt(vehicleDTO.getCreatedAt());

        Vehicle updateVehicle = vehicleRepository.save(vehicle);
        VehicleResponse vehicleResponse = vehicleMapper.toResponse(updateVehicle);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_UPDATED_SUCCESS, vehicleResponse);
    }

    public ResDTO<?> deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicleRepository.deleteById(id);
        return new ResDTO<>(HttpServletResponse.SC_NO_CONTENT, MessageCode.VEHICLE_DELETED_SUCCESS, null);
    }

    public ResDTO<?> searchVehicles(VehicleSearchRequest req) {
        List<Vehicle> vehicles = vehicleRepository.searchVehicles(req);
        List<VehicleResponse> vehiclesResponses = vehicleMapper.toResponse(vehicles);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehiclesResponses);
    }

    public ResDTO<?> getCustomVehicles() {
        Double priceThreshold = 10_000_000.0;
        String brandPrefix = "S";
        String brandType = "BUS";

        List<Vehicle> vehicles = vehicleRepository.findVehiclesByCustomCondition(priceThreshold, brandPrefix, brandType);
        List<VehicleResponse> vehiclesResponses = vehicleMapper.toResponse(vehicles);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehiclesResponses);
    }
}
