package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/category")
//    public List<Category> findall() {
//        return categoryService.findAll();
//    }

    @GetMapping("/add-category")
    public String showAddCategory(Model model) {
        model.addAttribute("categoryDTO", new Category());
        return "function-admin/add-category";
    }

    @PostMapping("/category_save")
    public String addCategory(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return "redirect:/admin";
    }

//    @PostMapping("/addCatagory")
//    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
//        String mess=categoryService.save(categoryDTO);
//        return mess;
//    }

//    @PutMapping("/updateCategory")
//    public String updateCategory(@RequestParam("id") int id,@RequestBody CategoryDTO categoryDTO) {
//        String mess=categoryService.update(id,categoryDTO);
//        return mess;
//    }

    @GetMapping("/update-category/{id}")
    public String UpdateCategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Category category = categoryService.findById(id);
        model.addAttribute("categoryDTO", category);
        return "function-admin/update-category";
    }

    // sửa sinh viên
    @PostMapping("/update-category/{id}")
    public String UpdateCategory(@PathVariable("id") int id, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return "redirect:/admin";
    }

    //    @DeleteMapping("/deleteCategory")
//    public String deleteCategory(@RequestParam("id") int id) {
//        String mess=categoryService.delete(id);
//        return mess;
//    }
    @GetMapping("/delete-category/{id}")
    public String deletecategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("id", id);
        Category category = categoryService.findById(id);
        model.addAttribute("categoryDTO", category);
        return "function-admin/delete-category";
    }

    // Xóa sinh viên theo ID
    @PostMapping("/delete-category/{id}")
    public String deletecategory(@PathVariable("id") int id, @ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.delete(id);
        return "redirect:/admin";
    }

}
