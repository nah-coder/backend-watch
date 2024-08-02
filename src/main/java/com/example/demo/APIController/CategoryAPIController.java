package com.example.demo.APIController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPIController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}/products")
    public CategoryDTO getCategoryWithProducts(@PathVariable Integer categoryId) {
//        return categoryService.getCategoryWithProducts(categoryId);
        return null;
    }
}
