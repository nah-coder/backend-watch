package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.Payment_MethodDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.PaymentMethod;
import com.example.demo.repository.Payment_MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Payment_MethodService {
    @Autowired
    private Payment_MethodRepository payment_methodRepository;

    public List<PaymentMethod> findAll() {
        return payment_methodRepository.findAll();
    }

    public PaymentMethod findById(int id) {
        Optional<PaymentMethod> paymentMethod = payment_methodRepository.findById(id);
        if(paymentMethod.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return paymentMethod.get();
    }

    public Page<PaymentMethod> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return  payment_methodRepository.findAll(pageable);
    }

    public List<PaymentMethod> searchPaymentMethod(String keyword){
        return  payment_methodRepository.searchPaymentMethodBy(keyword);
    }

    public Page<PaymentMethod> searchPaymentMethod(String keyword,Integer pageno){
        List list = this.searchPaymentMethod(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<PaymentMethod>(list,pageable,this.searchPaymentMethod(keyword).size());
    }

    public String save(Payment_MethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentMethodDTO.getId());
        paymentMethod.setName(paymentMethodDTO.getName());
        paymentMethod.setUrl(paymentMethodDTO.getUrl());
        paymentMethod.setCreatedDate(paymentMethodDTO.getCreatedDate());
        paymentMethod.setUpdatedDate(paymentMethodDTO.getUpdatedDate());
        paymentMethod.setIsactive(paymentMethodDTO.getIsactive());
        payment_methodRepository.save(paymentMethod);
        return "thêm thành công";
    }

    public String update(int id,Payment_MethodDTO paymentMethodDTO) {
        boolean exists = payment_methodRepository.existsById(id);
        if(!exists) {
            return "không tìm thấy id";
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentMethodDTO.getId());
        paymentMethod.setName(paymentMethodDTO.getName());
        paymentMethod.setUrl(paymentMethodDTO.getUrl());
        paymentMethod.setCreatedDate(paymentMethodDTO.getCreatedDate());
        paymentMethod.setUpdatedDate(paymentMethodDTO.getUpdatedDate());
        paymentMethod.setIsactive(paymentMethodDTO.getIsactive());
        payment_methodRepository.save(paymentMethod);
        return "Update thành công";
    }

    public String delete(int id) {
        boolean exists = payment_methodRepository.existsById(id);
        if (!exists){
            return "không tìm thấy id";
        }
        payment_methodRepository.deleteById(id);
        return "Xóa thành công";
    }
}
