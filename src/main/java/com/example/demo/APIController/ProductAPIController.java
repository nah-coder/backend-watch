//package com.example.demo.APIController;
//
//import com.example.demo.entity.Product;
//import com.example.demo.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//@RestController
//@RequestMapping("/api/products")
//public class ProductAPIController {
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/category/{categoryId}")
//    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
//        return productService.getProductsByCategory(categoryId);
//    }
//}
