package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;


@Controller
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    protected StorageService storageService;
    @Autowired
    private ProductService productService;
    @GetMapping("/add-product")
    public String showAddProduct(Model model ) {
        model.addAttribute("productDTO", new Product());
        model.addAttribute("categories", productService.findAllCategories());
        return "function-admin/add-product";
    }

    @PostMapping("/product_save")
    public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("img") MultipartFile file) {
        productDTO.setImage(file.getOriginalFilename());
        storageService.store(file);
        productService.save(productDTO);
        return "redirect:/admin";
    }

//    @PostMapping("/product_save")
//    public String addProduct(@ModelAttribute("productDTO") Product product,@RequestParam("image") MultipartFile image,
//                             RedirectAttributes redirectAttributes) {
//        if (image.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:/Product/add";
//        }
//
//        // Lưu tệp vào thư mục
//        storageService.store(image);
//        // Lưu tên tệp vào productDTO
//        product.setImage(image.getOriginalFilename());
//        // Gọi service để lưu productDTO vào cơ sở dữ liệu
//        // productService.save(productDTO);
//        redirectAttributes.addFlashAttribute("message", "Product saved successfully!");
//        return "redirect:/admin";
//    }

    // sửa sinh viên

    @GetMapping("/update-product/{id}")
    public String updateProduct(Model model, @PathVariable("id") int id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = new ProductDTO();
        // map các trường từ product sang productDTO
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setQuatity(product.getQuatity());
        productDTO.setPrice(product.getPrice());
        productDTO.setIsactive(product.getIsactive());
        Category category = new Category();
        category.setId(product.getCategory().getId());
        category.setName(product.getCategory().getName());
        productDTO.setCategory(category);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("id", id);
        model.addAttribute("categories", productService.findAllCategories());
        return "function-admin/update-product";
    }
    @PostMapping("/update-product/{id}")
    public String Updateproduct(@PathVariable("id") int id, @ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("img") MultipartFile file) {
        productDTO.setImage(file.getOriginalFilename());
        storageService.store(file);
        productService.update(id, productDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteproduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Product product = productService.findById(id);
        model.addAttribute("productDTO", product);
        return "function-admin/delete-product";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id, @ModelAttribute("productDTO") ProductDTO productDTO) {
        productService.delete(id);
        return "redirect:/admin";
    }
}
