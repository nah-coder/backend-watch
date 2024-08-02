package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer idpayment;
    private Integer idtransport;
    private LocalDateTime ordersDate;
    private Integer idcustomer;
    private Double totalMoney;
    private String notes;
    private String nameReciver;
    private String address;
    private String phone;
}
