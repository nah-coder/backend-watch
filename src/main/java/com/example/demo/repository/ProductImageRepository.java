package com.example.demo.repository;

import com.example.demo.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImages, Integer> {
}
