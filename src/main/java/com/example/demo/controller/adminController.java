package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerSevice customerSevice;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Payment_MethodService paymentMethodService;
    @Autowired
    private Transport_MethodService transportMethodService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping()
    public String admin() {
        return "layout-admin/index";
    }

    @GetMapping("/category")
    public String findallCategory(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<Category> categories = categoryService.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            categories = this.categoryService.searchCategory(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("categories", categories);
        return "layout-admin/category";
    }

    @GetMapping("/customer")
    public String findallCustomer(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<Customer> customers = customerSevice.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            customers = this.customerSevice.searchCustomer(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", customers.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("customers", customers);
        return "layout-admin/customer";
    }

    @GetMapping("/order")
    public String findallOrder(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<Orders> orders = orderService.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            orders = this.orderService.searchOrder(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", orders.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("orders", orders);
        model.addAttribute("payment", new PaymentMethod());
        model.addAttribute("transport", new TransportMethod());
        return "layout-admin/order";
    }

    @GetMapping("/product")
    public String Product(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<Product> products = productService.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            products = this.productService.searchProduct(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("products", products);
        return "layout-admin/product";
    }

    @GetMapping("/payment")
    public String Payment(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<PaymentMethod> paymentMethods = paymentMethodService.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            paymentMethods = this.paymentMethodService.searchPaymentMethod(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", paymentMethods.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("paymentMethods", paymentMethods);
        return "layout-admin/payment";
    }

    @GetMapping("/transport")
    public String Transport(Model model , @Param("keyword") String keyword, @RequestParam(name = "pageno", defaultValue = "1") Integer pageno){
        Page<TransportMethod> transportMethods = transportMethodService.findAllpage(pageno);
        if (keyword != null && !keyword.isEmpty()) {
            transportMethods = this.transportMethodService.searchTransportMethod(keyword, pageno);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", transportMethods.getTotalPages());
        model.addAttribute("currentPage", pageno);
        model.addAttribute("transportMethods", transportMethods);
        return "layout-admin/transport";
    }

    @GetMapping("/orderdetail/{id}")
    public String OrderDetail(Model model, @PathVariable("id") int id){
//        Orders orders = orderService.findById(id);
        Orders orders = orderDetailService.findAllOrders(id); // Đây là đơn hàng tương ứng với idord
        OrdersDetails ordersDetails = orderDetailService.findById(id);
        List<Product> products = orderDetailService.getProductsByOrderId(id);
        model.addAttribute("ordersDetails", orders);
        model.addAttribute("ordersDetailsnew", ordersDetails);
        model.addAttribute("ordersProduct", products);
//        model.addAttribute("orders", orders);
        return "layout-admin/orderdetail";
    }
}
