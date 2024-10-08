package com.example.demo.repository;

import com.example.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    public Roles findByName(String name);
}
