package com.example.demo.repository;

import com.example.demo.entity.TransportMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Transport_MethodRepository extends JpaRepository<TransportMethod,Integer> {
    @Query("SELECT c FROM TransportMethod c WHERE c.name LIKE %?1%")
    List<TransportMethod> searchTransportMethodBy(String keyword);
}
