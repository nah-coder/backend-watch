package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.RegisterCustomer;
import com.example.demo.entity.Roles;
import com.example.demo.repository.RolesRepository;
import com.example.demo.service.CustomerServiceSrc;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private CustomerServiceSrc customerServiceSrc;
    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model) {
        RegisterCustomer ru = new RegisterCustomer();
        model.addAttribute("registerUser", ru);
        return "layout-user/Register";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") RegisterCustomer registerCustomer, BindingResult result, Model model
//            , HttpSession session
    ) {
        if (result.hasErrors()) {
            return "layout-user/Register";
        }

        String username = registerCustomer.getUsername();
        Customer existingCustomer = customerServiceSrc.findByUsername(username);
        if (existingCustomer != null) {
            model.addAttribute("registerUser", new RegisterCustomer());
            model.addAttribute("my_error", "Tài khoản đã tồn tại");
            return "layout-user/Register";
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Customer newCustomer = new Customer();
        newCustomer.setUsername(registerCustomer.getUsername());
        newCustomer.setPassword(bcrypt.encode(registerCustomer.getPassword()));
        newCustomer.setAddress(registerCustomer.getAddress());
        newCustomer.setEmail(registerCustomer.getEmail());
        newCustomer.setPhone(registerCustomer.getPhone());

        Roles defaultRole = rolesRepository.findByName("ROLE_USER");
        Collection<Roles> roles = new ArrayList<>();
        roles.add(defaultRole);
        newCustomer.setRole(roles);

        customerServiceSrc.save(newCustomer);

        // thông báo thành công
//        session.setAttribute("myuser", customer1);

        return "/layout-user/index";
    }
}
