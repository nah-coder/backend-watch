package com.example.demo.dto;

import com.example.demo.entity.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Integer quatity;
    private Byte isactive;
    private Category category;
}
