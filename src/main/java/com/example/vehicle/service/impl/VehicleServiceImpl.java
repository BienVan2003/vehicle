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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleServiceImpl implements VehicleService {
    VehicleRepository vehicleRepository;

    BrandRepository brandRepository;

    VehicleMapper vehicleMapper;

    public ResDTO<?> getVehicles() {
        List<VehicleResponse> vehicles = vehicleMapper.toResponse(vehicleRepository.findAll());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicles);
    }

    @Override
    public ResDTO<?> getDeletedVehicles() {
        List<VehicleResponse> vehicles = vehicleMapper.toResponse(vehicleRepository.findByIsDeletedTrue());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicles);
    }

    @Override
    public ResDTO<?> getActiveVehicles() {
        List<VehicleResponse> vehicles = vehicleMapper.toResponse(vehicleRepository.findByIsDeletedFalseOrIsDeletedIsNull());
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicles);
    }

    public ResDTO<?> getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findActiveById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        VehicleResponse vehicleRes = vehicleMapper.toResponse(vehicle);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_FETCHED, vehicleRes);
    }

    public ResDTO<?> createVehicle(VehicleRequest vehicleReq) {
        Vehicle vehicle = Vehicle.builder()
                .name(vehicleReq.getName())
                .year(vehicleReq.getYear())
                .price(vehicleReq.getPrice())
                .owner(vehicleReq.getOwner())
                .createdAt(Instant.now())
                .isDeleted(false)
                .build();

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
        Vehicle vehicle = vehicleRepository.findActiveById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicle.setName(vehicleDTO.getName());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setOwner(vehicleDTO.getOwner());

        Vehicle updateVehicle = vehicleRepository.save(vehicle);
        VehicleResponse vehicleResponse = vehicleMapper.toResponse(updateVehicle);
        return new ResDTO<>(HttpServletResponse.SC_OK, MessageCode.VEHICLE_UPDATED_SUCCESS, vehicleResponse);
    }

    public ResDTO<?> deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicle.setIsDeleted(true);
        vehicleRepository.save(vehicle);
        return new ResDTO<>(HttpServletResponse.SC_NO_CONTENT, MessageCode.VEHICLE_DELETED_SUCCESS, null);
    }

    public ResDTO<?> searchVehicles(VehicleSearchRequest req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());
        List<Vehicle> vehicles = vehicleRepository.searchVehicles(req, pageable);
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
