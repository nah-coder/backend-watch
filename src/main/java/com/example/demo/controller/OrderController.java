package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/Orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/add-orders")
    public String showAddOrders(Model model) {
        // Tạo đối tượng ngày giờ hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = now.format(formatter);

        // Thêm giá trị ngày giờ vào model
        model.addAttribute("currentDateTime", formattedNow);
        model.addAttribute("ordersDTO", new Orders());
        return "function-admin/add-orders";
    }

    @PostMapping("/orders_save")
    public String addOrders(@ModelAttribute("ordersDTO") OrderDTO orderDTO) {
        orderService.save(orderDTO);
        return "redirect:/admin";
    }

    // sửa sinh viên
    @GetMapping("/update-orders/{id}")
    public String UpdateOrders(Model model, @PathVariable("id") int id) {
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = now.format(formatter);

        // Thêm giá trị ngày giờ vào model
        model.addAttribute("currentDateTime", formattedNow);
        model.addAttribute("id", id);
        Orders orders = orderService.findById(id);
        model.addAttribute("orderDTO", orders);
        return "function-admin/update-orders";
    }
    @PostMapping("/update-orders/{id}")
    public String UpdateOrders(@PathVariable("id") int id, @ModelAttribute("orderDTO") OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-orders/{id}")
    public String deleteOrders(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Orders orders = orderService.findById(id);
        model.addAttribute("orderDTO", orders);
        return "function-admin/delete-orders";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-orders/{id}")
    public String deleteOrders(@PathVariable("id") int id, @ModelAttribute("orderDTO") OrderDTO orderDTO) {
        orderService.delete(id);
        return "redirect:/admin";
    }
}
