package com.example.demo.controller;

import com.example.demo.dto.Payment_MethodDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.PaymentMethod;
import com.example.demo.entity.Product;
import com.example.demo.service.Payment_MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Payment")
public class Payment_MethodController {
    @Autowired
    private Payment_MethodService payment_methodService;
    @GetMapping("/add-payment")
    public String showAddPayment(Model model) {
        model.addAttribute("paymentDTO", new PaymentMethod());
        return "function-admin/add-payment";
    }

    @PostMapping("/payment_save")
    public String addpayment(@ModelAttribute("paymentDTO") Payment_MethodDTO paymentMethodDTO) {
        payment_methodService.save(paymentMethodDTO);
        return "redirect:/admin";
    }

    // sửa sinh viên

    @GetMapping("/update-payment/{id}")
    public String Updatepayment(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        PaymentMethod paymentMethod = payment_methodService.findById(id);
        model.addAttribute("paymentDTO", paymentMethod);
        return "function-admin/update-payment";
    }
    @PostMapping("/update-payment/{id}")
    public String UpdatePayment(@PathVariable("id") int id, @ModelAttribute("paymentDTO") Payment_MethodDTO paymentMethodDTO) {
        payment_methodService.update(id, paymentMethodDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-payment/{id}")
    public String deletePayment(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        PaymentMethod paymentMethod = payment_methodService.findById(id);
        model.addAttribute("paymentDTO", paymentMethod);
        return "function-admin/delete-payment";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-payment/{id}")
    public String deletePayment(@PathVariable("id") int id, @ModelAttribute("paymentDTO") Payment_MethodDTO paymentMethodDTO) {
        payment_methodService.delete(id);
        return "redirect:/admin";
    }
}