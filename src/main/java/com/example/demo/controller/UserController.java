package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Category;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private Transport_MethodService transportMethodService;
    @Autowired
    private Payment_MethodService paymentMethodService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Shopping_cartimpl shoppingCartimpl;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private OrderService orderService;
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

    @GetMapping("/logout")
    public String showlogout() {
        return "layout-user/logout";
    }

    @GetMapping("/confirm")
    public String showConfirm(){
        return "layout-user/confirmation";
    }

    @GetMapping("/checkout")
    public String showCheckOut(
//            @RequestParam("id") int id,
            Model model
    ){
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ theo định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = now.format(formatter);

        // Thêm giá trị ngày giờ vào model
        model.addAttribute("currentDateTime", formattedNow);
        model.addAttribute("OrderCheck",new Orders());
//        model.addAttribute("order", productService.findById(id));
        model.addAttribute("CART_ITEM", shoppingCartimpl.getAllItem());
        model.addAttribute("TOTAL",shoppingCartimpl.getTotal());
//        return "layout-user/checkout";
        return "layout-user/checkout";
    }

//    @PostMapping("/savecheckout")
//    public String addOrders(@ModelAttribute("ordersDTO") OrderDTO orderDTO) {
//        Orders orders = new Orders();
//        orders.setOrdersDate(orderDTO.getOrdersDate());
////        orders.setOrdersDate(new LocalDate(System.currentTimeMillis()));
//        orders.setNameReciver(orderDTO.getNameReciver());
//        orders.setNotes(orderDTO.getNotes());
//        orders.setAddress(orderDTO.getAddress());
//        orders.setTotalMoney(orderDTO.getTotalMoney());
//        orders.setPhone(orderDTO.getPhone());
//        Collection<CartItem> cartItems = shoppingCartimpl.getAllItem();
//
//        // Gán mỗi CartItem vào Order
//        for (CartItem item : cartItems) {
//            item.setOrder(orders);  // Gán Order vào CartItem
//        }
//
//        // Gán danh sách CartItem vào Order
//        orders.setCartItems((List<CartItem>) cartItems);
//        orderService.save(orderDTO);
//        shoppingCartimpl.clear();
//        return "layout-user/confirmation";
//    }

    @PostMapping("/savecheckout")
    public String addOrders(@ModelAttribute("ordersDTO") OrderDTO orderDTO
//            ,@PathVariable int id,Model model
    ) {
        Orders orders = new Orders();
        orders.setOrdersDate(orderDTO.getOrdersDate());
        orders.setNameReciver(orderDTO.getNameReciver());
        orders.setNotes(orderDTO.getNotes());
        orders.setAddress(orderDTO.getAddress());
        orders.setTotalMoney(orderDTO.getTotalMoney());
        orders.setPhone(orderDTO.getPhone());

        // Lấy tất cả các CartItem từ giỏ hàng
        Collection<CartItem> cartItems = shoppingCartimpl.getAllItem();

        // Gán mỗi CartItem vào Order
        for (CartItem item : cartItems) {
            item.setOrder(orders);  // Gán Order vào CartItem
        }

        // Chuyển Collection<CartItem> sang List<CartItem>
        List<CartItem> cartItemList = new ArrayList<>(cartItems);

        // Gán danh sách CartItem vào Order
        orders.setCartItems(cartItemList);

        // Lưu Order
        orderService.save(orderDTO);

//        model.addAttribute("id", id);
//        Orders orders1 = orderService.findById(id);
//        model.addAttribute("OrderDTO", orders1);

        // Xóa giỏ hàng sau khi đặt hàng thành công
        shoppingCartimpl.clear();

        return "layout-user/confirmation";
    }


    @GetMapping("/show_cart")
    public String showcart(Model model) {
        model.addAttribute("PAYMENT",paymentMethodService.findAll());
        model.addAttribute("TRANSPORT",transportMethodService.findAll());
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

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty){
        shoppingCartimpl.update(id,qty);
        return "redirect:/user/show_cart";
    }

    @PostMapping("/remove/{productId}")
    public String removecart(@PathVariable("productId") Integer productId) {
        shoppingCartimpl.remove(productId);
        return "redirect:/user/show_cart";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "/layout-user/login";
    }
//
//    @GetMapping("/showPage403")
//    public String showPage403(){
//        return "error/403";
//    }
}
