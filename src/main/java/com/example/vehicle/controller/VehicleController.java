package com.example.vehicle.controller;

import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.*;
import com.example.vehicle.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<?> getAllVehicles(@RequestParam(required = false) Boolean isDeleted) {
        ResDTO<?> res = (isDeleted == null)
                ? vehicleService.getVehicles() :
                (isDeleted ? vehicleService.getDeletedVehicles() : vehicleService.getActiveVehicles());
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        ResDTO<?> res = vehicleService.getVehicleById(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicleDTO) {
        ResDTO<?> res = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequest vehicleDTO) {
        ResDTO<?> res = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        ResDTO<?> res = vehicleService.deleteVehicle(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchVehicles(@Valid @ModelAttribute VehicleSearchRequest vehicleSearchRequest) {
        ResDTO<?> res = vehicleService.searchVehicles(vehicleSearchRequest);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getVehiclesByCustomCondition() {
        ResDTO<?> res = vehicleService.getCustomVehicles();
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
