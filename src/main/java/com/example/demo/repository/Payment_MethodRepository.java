package com.example.demo.repository;

import com.example.demo.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Payment_MethodRepository extends JpaRepository<PaymentMethod,Integer> {
    @Query("SELECT c FROM PaymentMethod c WHERE c.name LIKE %?1%")
    List<PaymentMethod> searchPaymentMethodBy(String keyword);
}
