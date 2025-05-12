package com.example.vehicle.repository;

import com.example.vehicle.entity.Brand;
import com.example.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByIsDeletedTrue();
    List<Brand> findByIsDeletedFalseOrIsDeletedIsNull();
    @Query("SELECT v FROM Brand v WHERE v.id = :id AND (v.isDeleted = false OR v.isDeleted IS NULL)")
    Optional<Brand> findActiveById(@Param("id") Long id);
}
