package com.example.demo.repository;

import com.example.demo.entity.Orders;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT c FROM Orders c WHERE c.nameReciver LIKE %?1%")
    List<Orders> searchOrdersBy(String keyword);
}
