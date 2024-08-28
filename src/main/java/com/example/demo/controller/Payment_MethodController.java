package com.example.demo.controller;

import com.example.demo.dto.Payment_MethodDTO;
import com.example.demo.entity.PaymentMethod;
import com.example.demo.service.Payment_MethodService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/Payment")
public class Payment_MethodController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private Payment_MethodService payment_methodService;
    @GetMapping("/add-payment")
    public String showAddPayment(Model model) {
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = now.format(formatter);

        // Thêm giá trị ngày giờ vào model
        model.addAttribute("currentDateTime", formattedNow);
        model.addAttribute("paymentDTO", new PaymentMethod());
        return "function-admin/add-payment";
    }

    @PostMapping("/payment_save")
    public String addpayment(@ModelAttribute("paymentDTO") Payment_MethodDTO paymentMethodDTO, @RequestParam("urls") MultipartFile file) {
        paymentMethodDTO.setUrl(file.getOriginalFilename());
        storageService.store(file);
        payment_methodService.save(paymentMethodDTO);
        return "redirect:/admin";
    }

    // sửa sinh viên

    @GetMapping("/update-payment/{id}")
    public String Updatepayment(Model model, @PathVariable("id") int id) {
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = now.format(formatter);

        // Thêm giá trị ngày giờ vào model
        model.addAttribute("currentDateTime", formattedNow);
        model.addAttribute("id", id);
        PaymentMethod paymentMethod = payment_methodService.findById(id);
        model.addAttribute("paymentDTO", paymentMethod);
        return "function-admin/update-payment";
    }
    @PostMapping("/update-payment/{id}")
    public String UpdatePayment(@PathVariable("id") int id, @ModelAttribute("paymentDTO") Payment_MethodDTO paymentMethodDTO, @RequestParam("urls") MultipartFile file) {
        paymentMethodDTO.setUrl(file.getOriginalFilename());
        storageService.store(file);
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
