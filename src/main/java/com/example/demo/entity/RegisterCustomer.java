package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterCustomer {
    @NotBlank(message = "Thông Tin Bắt Buộc")
    @Size(min = 1,message = "Độ dài tối thiểu là 1")
    private String username;
    @NotBlank(message = "Thông Tin Bắt Buộc")
    @Size(min = 8,message = "Độ dài tối thiểu là 8")
    private String password;
    @NotBlank(message = "Thông Tin Bắt Buộc")
    private String address;
    @NotBlank(message = "Thông Tin Bắt Buộc")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotBlank(message = "Thông Tin Bắt Buộc")
    private String phone;
}
