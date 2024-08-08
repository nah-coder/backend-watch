package com.example.demo.service;
import com.example.demo.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerServiceSrc extends UserDetailsService {
    public Customer findByUsername(String username);
    public Customer save(Customer customer);

}
