package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.ProductImages;
import com.example.demo.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    public List<ProductImages> findall(){
        return productImageRepository.findAll();
    }
}
