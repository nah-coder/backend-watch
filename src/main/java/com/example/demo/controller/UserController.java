package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImages;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;
    @GetMapping()
    public String user() {
        return "layout-user/index";
    }

    @GetMapping("/shop")
    public String showshop(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("product_all", products);
        return "layout-user/shop";
    }

    @GetMapping("/about")
    public String showabout() {
        return "layout-user/about";
    }

    @GetMapping("/blog")
    public String showblog() {
        return "layout-user/blog";
    }

    @GetMapping("/blogdetail")
    public String showblogdetail() {
        return "layout-user/blog-details";
    }

    @GetMapping("/contact")
    public String showcontact() {
        return "layout-user/contact";
    }

    @GetMapping("/product_details")
    public String showproduct_details( Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("product", products);
//        System.out.println(products);
//        model.addAttribute("productImage", productImages);
        return "layout-user/product_details";
    }

    @GetMapping("/login")
    public String showlogin() {
        return "layout-user/login";
    }

    @GetMapping("/cart")
    public String showcart() {
        return "layout-user/cart";
    }
}
