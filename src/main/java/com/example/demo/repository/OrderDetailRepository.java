package com.example.demo.repository;

import com.example.demo.entity.*;
import com.example.demo.service.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrdersDetails,Integer> {
    @Query("SELECT o.products FROM OrdersDetails o WHERE o.orders.id = :idord")
    List<Product> findProductsByOrderId(int idord);
//
//    @Query("SELECT o.orders FROM OrdersDetails o WHERE o.orders.id = :idord")
//    List<Product> findOrderByOrderId(int idproduct);

    @Query("SELECT od.orders FROM OrdersDetails od WHERE od.orders.id = :idord")
    Orders findOrdersById(@Param("idord") Integer idord);

    @Query("SELECT od.payment FROM OrdersDetails od WHERE od.orders.id = :idord")
    PaymentMethod findPaymentById(@Param("idord") Integer idord);

    @Query("SELECT od.transport FROM OrdersDetails od WHERE od.orders.id = :idord")
    TransportMethod findTransportById(@Param("idord") Integer idord);
}
