package com.example.vehicle.controller;

import com.example.vehicle.dto.ResDTO;
import com.example.vehicle.dto.request.*;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        ResDTO<?> res = vehicleService.getAllVehicles();
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

    @PostMapping("/search")
    public ResponseEntity<?> searchVehicles(@RequestBody VehicleSearchRequest req) {
        ResDTO<?> res = vehicleService.searchVehicles(req);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getVehiclesByCustomCondition() {
        ResDTO<?> res = vehicleService.getCustomVehicles();
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
