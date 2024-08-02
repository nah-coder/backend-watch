package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String role;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime createdDate;
    private byte isactive;
}
