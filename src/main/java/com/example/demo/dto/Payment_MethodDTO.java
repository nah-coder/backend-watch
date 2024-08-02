package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment_MethodDTO {
    private Integer id;
    private String name;
    private String url;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private byte isactive;
}
