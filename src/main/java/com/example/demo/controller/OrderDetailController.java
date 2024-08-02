package com.example.demo.controller;

import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
