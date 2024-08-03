package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
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
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository; // Khai báo CategoryRepository
//
//    public List<Product> getProductsByCategory(Integer categoryId) {
//        return productRepository.findByCategoryId(categoryId);
//    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return product.get();
    }

    public Page<Product> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return  productRepository.findAll(pageable);
    }
    public List<Product> searchProduct(String keyword){
        return  productRepository.searchProduct(keyword);
    }
    public Page<Product> searchProduct(String keyword,Integer pageno){
        List list = this.searchProduct(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Product>(list,pageable,this.searchProduct(keyword).size());
    }

    public String save(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setQuatity(productDTO.getQuatity());
        product.setPrice(productDTO.getPrice());
        product.setIsactive(productDTO.getIsactive());
        product.setCategory(productDTO.getCategory());
        Optional<Category> category = categoryRepository.findById(productDTO.getCategory().getId());
        if (category.isPresent()) {
            product.setCategory(category.get());
        } else {
            return "Không tìm thấy Category";
        }
        productRepository.save(product);
        return "Save thành công";
    }

    public String update(int id, ProductDTO productDTO) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            return "không tìm thấy id";
        }
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setQuatity(productDTO.getQuatity());
        product.setPrice(productDTO.getPrice());
        product.setIsactive(productDTO.getIsactive());
        product.setCategory(productDTO.getCategory());
        Optional<Category> category = categoryRepository.findById(productDTO.getCategory().getId());
        if (category.isPresent()) {
            product.setCategory(category.get());
        } else {
            return "Không tìm thấy Category";
        }
        productRepository.save(product);
        return "Update thành công";
    }

    public String delete(int id) {
        boolean exists = productRepository.existsById(id);
        if (!exists){
            return "không tìm thấy id";
        }
        productRepository.deleteById(id);
        return "Xóa thành công";
    }
}
