package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Integer idord;
    private Integer idproduct;
    private Double price;
}
