package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryDTO {
    private int id;
    private Integer idparent;
    private String name;
    private Integer quantity;
    private String description;
    private Byte isactive;
    List<Product> products;
//
//
//    public CategoryDTO(int id, String name, List<Product> products) {
//        this.id = id;
//        this.name = name;
//        this.products = products;
//    }

}
