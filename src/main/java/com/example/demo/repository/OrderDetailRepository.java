package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Orders;
import com.example.demo.entity.OrdersDetails;
import com.example.demo.entity.Product;
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

    @Query("SELECT o.orders FROM OrdersDetails o")
    public Orders findAllOrders(int id);

}
