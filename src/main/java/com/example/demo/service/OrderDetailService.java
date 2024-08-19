package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.entity.OrdersDetails;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrdersDetails> findAll() {
        return orderDetailRepository.findAll();
    }

    public OrdersDetails findById(int id) {
        Optional<OrdersDetails> ordersDetails = orderDetailRepository.findById(id);
        if(ordersDetails.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return ordersDetails.get();
    }

    public List<Product> getProductsByOrderId(int idord) {
        return orderDetailRepository.findProductsByOrderId(idord);
    }

    public Orders findAllOrders(int id) {
        return orderDetailRepository.findAllOrders(id);
    }
}
