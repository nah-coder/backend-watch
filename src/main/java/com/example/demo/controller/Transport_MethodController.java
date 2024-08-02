package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.Transport_MethodDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.TransportMethod;
import com.example.demo.service.Transport_MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Transport")
public class Transport_MethodController {
    @Autowired
    private Transport_MethodService transportMethodService;
    @GetMapping("/add-transport")
    public String showAddTransport(Model model) {
        model.addAttribute("transportDTO", new TransportMethod());
        return "function-admin/add-transport";
    }

    @PostMapping("/transport_save")
    public String addTransport(@ModelAttribute("transportDTO") Transport_MethodDTO transportMethodDTO) {
        transportMethodService.save(transportMethodDTO);
        return "redirect:/admin";
    }

    // sửa sinh viên

    @GetMapping("/update-transport/{id}")
    public String UpdateTransport(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        TransportMethod transportMethod = transportMethodService.findById(id);
        model.addAttribute("transportDTO", transportMethod);
        return "function-admin/update-transport";
    }
    @PostMapping("/update-transport/{id}")
    public String UpdateTransport(@PathVariable("id") int id, @ModelAttribute("transportDTO") Transport_MethodDTO transportMethodDTO) {
        transportMethodService.update(id, transportMethodDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-transport/{id}")
    public String deleteTransport(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        TransportMethod transportMethod = transportMethodService.findById(id);
        model.addAttribute("transportDTO", transportMethod);
        return "function-admin/delete-transport";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-transport/{id}")
    public String deleteTransport(@PathVariable("id") int id, @ModelAttribute("transportDTO") Transport_MethodDTO transportMethodDTO) {
        transportMethodService.delete(id);
        return "redirect:/admin";
    }
}
