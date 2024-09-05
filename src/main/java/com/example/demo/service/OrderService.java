package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Category;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Shopping_cartimpl shoppingCartimpl;

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public Page<Orders> findAllpage(Integer pageno){
        Pageable pageable = PageRequest.of(pageno-1,4);
        return orderRepository.findAll(pageable);
    }

    public List<Orders> searchOrder(String keyword){
        return  orderRepository.searchOrdersBy(keyword);
    }

    public Page<Orders> searchOrder(String keyword,Integer pageno){
        List list = this.searchOrder(keyword);
        Pageable pageable = PageRequest.of(pageno-1,2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int)(pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Orders>(list,pageable,this.searchOrder(keyword).size());
    }

    public Orders findById(int id) {
        Optional<Orders> orders = orderRepository.findById(id);
        if(orders.isEmpty()) {
            System.out.println("Không tìm thấy id");
            return null;
        }
        return orders.get();
    }

    public String save(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setOrdersDate(orderDTO.getOrdersDate());
//        orders.setOrdersDate(new LocalDate(System.currentTimeMillis()));
        orders.setNameReciver(orderDTO.getNameReciver());
        orders.setNotes(orderDTO.getNotes());
        orders.setAddress(orderDTO.getAddress());
        orders.setTotalMoney(orderDTO.getTotalMoney());
        orders.setPhone(orderDTO.getPhone());
//        Collection<CartItem> cartItems = shoppingCartimpl.getAllItem();
//
//        // Gán mỗi CartItem vào Order
//        for (CartItem item : cartItems) {
//            item.setOrder(orders);  // Gán Order vào CartItem
//        }
//
//        // Gán danh sách CartItem vào Order
//        orders.setCartItems((List<CartItem>) cartItems);
        orderRepository.save(orders);
        return "thêm thành công";
    }

    public String update(int id,OrderDTO orderDTO) {
        boolean exists = orderRepository.existsById(id);
        if(!exists) {
            return "không tìm thấy id";
        }
        Orders orders = new Orders();
        orders.setId(orderDTO.getId());
        orders.setOrdersDate(orderDTO.getOrdersDate());
        orders.setNameReciver(orderDTO.getNameReciver());
        orders.setNotes(orderDTO.getNotes());
        orders.setAddress(orderDTO.getAddress());
        orders.setTotalMoney(orderDTO.getTotalMoney());
        orders.setPhone(orderDTO.getPhone());
        orderRepository.save(orders);
        return "Update thành công";
    }

    public String delete(int id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists){
            return "không tìm thấy id";
        }orderRepository.deleteById(id);
        return "Xóa thành công";
    }
}
