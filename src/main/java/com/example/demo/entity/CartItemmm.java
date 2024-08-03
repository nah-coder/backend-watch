package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemmm {
    private Integer productId;
    private String name;
    private int qty=1;
    private double price;
    private String image;
}
