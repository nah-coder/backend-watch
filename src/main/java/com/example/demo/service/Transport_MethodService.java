package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.Transport_MethodDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.TransportMethod;
import com.example.demo.repository.Transport_MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Transport_MethodService {
    @Autowired
    private Transport_MethodRepository transport_MethodRepository;

    public List<TransportMethod> findAll() {
        return transport_MethodRepository.findAll();
    }

    public TransportMethod findById(int id) {
        Optional<TransportMethod> transportMethod = transport_MethodRepository.findById(id);
        if(transportMethod.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return transportMethod.get();
    }

    public Page<TransportMethod> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return  transport_MethodRepository.findAll(pageable);
    }

    public List<TransportMethod> searchTransportMethod(String keyword){
        return  transport_MethodRepository.searchTransportMethodBy(keyword);
    }

    public Page<TransportMethod> searchTransportMethod(String keyword,Integer pageno){
        List list = this.searchTransportMethod(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<TransportMethod>(list,pageable,this.searchTransportMethod(keyword).size());
    }

    public String save(Transport_MethodDTO transportMethodDTO) {
        TransportMethod transportMethod = new TransportMethod();
        transportMethod.setName(transportMethodDTO.getName());
        transportMethod.setNotes(transportMethodDTO.getNotes());
        transportMethod.setCreatedDate(transportMethodDTO.getCreatedDate());
        transportMethod.setUpdatedDate(transportMethodDTO.getUpdatedDate());
        transportMethod.setIsactive(transportMethodDTO.getIsactive());
        transport_MethodRepository.save(transportMethod);
        return "thêm thành công";
    }

    public String update(int id,Transport_MethodDTO transportMethodDTO) {
        boolean exists = transport_MethodRepository.existsById(id);
        if(!exists) {
            return "không tìm thấy id";
        }
        TransportMethod transportMethod = new TransportMethod();
        transportMethod.setId(transportMethodDTO.getId());
        transportMethod.setName(transportMethodDTO.getName());
        transportMethod.setNotes(transportMethodDTO.getNotes());
        transportMethod.setCreatedDate(transportMethodDTO.getCreatedDate());
        transportMethod.setUpdatedDate(transportMethodDTO.getUpdatedDate());
        transportMethod.setIsactive(transportMethodDTO.getIsactive());
        transport_MethodRepository.save(transportMethod);
        return "Update thành công";
    }

    public String delete(int id) {
        boolean exists = transport_MethodRepository.existsById(id);
        if (!exists){
            return "không tìm thấy id";
        }
        transport_MethodRepository.deleteById(id);
        return "Xóa thành công";
    }
}
