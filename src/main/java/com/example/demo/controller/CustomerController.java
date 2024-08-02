package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    private CustomerSevice customerSevice;

    @GetMapping("/add-customer")
    public String showAddCustomer(Model model) {
        model.addAttribute("customerDTO", new Customer());
        return "function-admin/add-customer";
    }

    @PostMapping("/customer_save")
    public String addCustomer(@ModelAttribute("customerDTO") CustomerDTO customerDTO) {
        customerSevice.saveCustomer(customerDTO);
        return "redirect:/admin";
    }

    @GetMapping("/update-customer/{id}")
    public String UpdateCustomer(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Customer customer = customerSevice.findCustomerById(id);
        model.addAttribute("customerDTO", customer);
        return "function-admin/update-customer";
    }

    // sửa sinh viên
    @PostMapping("/update-customer/{id}")
    public String UpdateCustomer(@PathVariable("id") int id, @ModelAttribute("customerDTO") CustomerDTO customerDTO) {
        customerSevice.updateCustomer(id, customerDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-customer/{id}")
    public String deletecategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Customer customer = customerSevice.findCustomerById(id);
        model.addAttribute("customerDTO", customer);
        return "function-admin/delete-customer";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-customer/{id}")
    public String deletecustomer(@PathVariable("id") int id, @ModelAttribute("customerDTO") CustomerDTO customerDTO) {
        customerSevice.deleteCustomer(id);
        return "redirect:/admin";
    }
}
