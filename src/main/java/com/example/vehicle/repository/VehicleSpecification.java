package com.example.vehicle.repository;

import com.example.vehicle.entity.Vehicle;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecification {

    public static Specification<Vehicle> hasBrand(String brandName) {
        return (root, query, builder) -> {
            if (brandName == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("brand").get("name"), brandName);
        };
    }

    public static Specification<Vehicle> hasYear(Integer year) {
        return (root, query, builder) -> {
            if (year == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("year"), year);
        };
    }

    public static Specification<Vehicle> hasPrice(Double price) {
        return (root, query, builder) -> {
            if (price == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("price"), price);
        };
    }

    public static Specification<Vehicle> hasOwner(String owner) {
        return (root, query, builder) -> {
            if (owner == null || owner.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(root.get("owner"), "%" + owner + "%");
        };
    }

    public static Specification<Vehicle> customPriceAndBrandCondition() {
        return (root, query, builder) -> {
            // Điều kiện price > 10_000_000 và tên hãng bắt đầu bằng 'S'
            var priceGreaterThan = builder.greaterThan(root.get("price"), 10_000_000);
            var brandNameStartsWithS = builder.like(root.get("brand").get("name"), "S%");

            // Điều kiện price <= 10_000_000 và loại hãng là BUS
            var priceLessThanOrEqual = builder.lessThanOrEqualTo(root.get("price"), 10_000_000);
            var brandTypeIsBus = builder.equal(root.get("brand").get("type"), "BUS");

            // Kết hợp 2 nhánh lại:
            // (price > 10_000_000 AND brand name starts with 'S') OR (price <= 10_000_000 AND brand type = BUS)
            return builder.or(
                    builder.and(priceGreaterThan, brandNameStartsWithS),
                    builder.and(priceLessThanOrEqual, brandTypeIsBus)
            );
        };
    }
}
