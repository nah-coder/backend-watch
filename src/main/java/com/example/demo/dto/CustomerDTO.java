package com.example.demo.dto;
import com.example.demo.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Collection<Roles> role;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime createdDate;
    private byte isactive;
}
