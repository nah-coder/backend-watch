package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport_MethodDTO {
    private Integer id;
    private String name;
    private String notes;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Byte isactive;
}
