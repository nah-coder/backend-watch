package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Page<Category> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return  categoryRepository.findAll(pageable);
    }

    public List<Category> searchCategory(String keyword){
        return  categoryRepository.searchCategory(keyword);
    }

    public Page<Category> searchCategory(String keyword,Integer pageno){
        List list = this.searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Category>(list,pageable,this.searchCategory(keyword).size());
    }

    public Category findById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return category.get();
    }

    public String save(CategoryDTO categoryDTO) {
        Category category = new Category();
//        category.setId(categoryDTO.getId());
        category.setIdparent(categoryDTO.getIdparent());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setQuantity(categoryDTO.getQuantity());
        category.setIsactive(categoryDTO.getIsactive());
        categoryRepository.save(category);
        return "thêm thành công";
    }

    public String update(int id,CategoryDTO categoryDTO) {
        boolean exists = categoryRepository.existsById(id);
        if(!exists) {
            return "không tìm thấy id";
        }
        Category category = new Category();
        category.setId(id);
        category.setIdparent(categoryDTO.getIdparent());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setQuantity(categoryDTO.getQuantity());
        category.setIsactive(categoryDTO.getIsactive());
        categoryRepository.save(category);
        return "Update thành công";
    }

    public String delete(int id) {
        boolean exists = categoryRepository.existsById(id);
        if (!exists){
            return "không tìm thấy id";
        }
        categoryRepository.deleteById(id);
        return "Xóa thành công";
    }
}
