package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
//
//    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Customer> customers;
}
