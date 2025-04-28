package com.example.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private double price;
    private String owner;
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}