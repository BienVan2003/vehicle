package com.example.vehicle.controller;

import com.example.vehicle.dto.VehicleDTO;
import com.example.vehicle.dto.VehicleSearchDTO;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestBody VehicleSearchDTO req) {
        List<Vehicle> vehicles = vehicleService.searchVehicles(req);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Vehicle>> getVehiclesByCustomCondition() {
        List<Vehicle> vehicles = vehicleService.getCustomVehicles();
        return ResponseEntity.ok(vehicles);
    }

}
