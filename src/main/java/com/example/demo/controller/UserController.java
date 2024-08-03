package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.CartItemmm;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import com.example.demo.service.Shopping_cartimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    private Shopping_cartimpl shoppingCartimpl;
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
    public String showProductDetails(@RequestParam("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "error/404"; // Hoặc trang lỗi khác phù hợp
        }
        model.addAttribute("product", product);
        return "layout-user/product_details";
    }

    @GetMapping("/login")
    public String showlogin() {
        return "layout-user/login";
    }

    @GetMapping("/confirm")
    public String showConfirm(){
        return "layout-user/confirmation";
    }

    @GetMapping("/checkout")
    public String showCheckOut(){
        return "layout-user/checkout";
    }

    @GetMapping("/show_cart")
    public String showcart(Model model) {
//        model.addAttribute("categoryDTO", new Category());
//        List<Product> products = productService.findAll();
        model.addAttribute("PAYMENT",shoppingCartimpl.findall());
        model.addAttribute("TRANSPORT",shoppingCartimpl.findAll());
        model.addAttribute("CART_ITEM", shoppingCartimpl.getAllItem());
        model.addAttribute("TOTAL",shoppingCartimpl.getTotal());
        return "/layout-user/cart";
    }

    @GetMapping("/add-cart/{id}")
    public String showaddcart(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        if(product != null){
            CartItem cartItem = new CartItem();
            cartItem.setProductId(product.getId());
            cartItem.setName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQty(1);
            cartItem.setImage(product.getImage());
            shoppingCartimpl.add(cartItem);
        }
        return "redirect:/user/show_cart";
    }

//    @GetMapping("/clear")
//    public String clearcart(){
//        shoppingCartimpl.clear();
//        return "redirect:/show_cart";
//    }

    @GetMapping("/remove/{productId}")
    public String removecart(@PathVariable("productId") Integer productId) {
        shoppingCartimpl.remove(productId);
        return "redirect:/show_cart";
    }
    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty){
        shoppingCartimpl.update(id,qty);
        return "redirect:/user/show_cart";
    }

}
