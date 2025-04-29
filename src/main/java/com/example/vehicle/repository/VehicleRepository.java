package com.example.vehicle.repository;

import com.example.vehicle.dto.VehicleSearchDTO;
import com.example.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("""
        SELECT v FROM Vehicle v 
        WHERE (:#{#req.brandName} IS NULL OR v.brand.name LIKE %:#{#req.brandName}%)
          AND (:#{#req.year} IS NULL OR v.year = :#{#req.year})
          AND (:#{#req.price} IS NULL OR v.price = :#{#req.price})
          AND (:#{#req.owner} IS NULL OR v.owner LIKE %:#{#req.owner}%)
    """)
    List<Vehicle> searchVehicles(@Param("req") VehicleSearchDTO req);

    @Query("""
        SELECT v FROM Vehicle v
        WHERE (v.price > :priceThreshold AND v.brand.name LIKE CONCAT(:brandPrefix, '%'))
           OR (v.price <= :priceThreshold AND v.brand.type = :brandType)
    """)
    List<Vehicle> findVehiclesByCustomCondition(
            @Param("priceThreshold") Double priceThreshold,
            @Param("brandPrefix") String brandPrefix,
            @Param("brandType") String brandType
    );

}