package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.CartItemmm;
import com.example.demo.entity.PaymentMethod;
import com.example.demo.entity.TransportMethod;
import com.example.demo.repository.Payment_MethodRepository;
import com.example.demo.repository.Transport_MethodRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Service
@SessionScope
public class Shopping_cartimpl implements Shopping_cartService{
    Map<Integer, CartItem> maps = new HashMap<>();
    @Autowired
    private Payment_MethodRepository paymentMethodRepository;
    @Autowired
    private Transport_MethodRepository transportMethodRepository;
    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getProductId());
        if(cartItem == null){
            maps.put(item.getProductId(), item);
        }else {
            cartItem.setQty(cartItem.getQty()+1);
        }
    }

    public List<PaymentMethod> findall(){
        return paymentMethodRepository.findAll();
    }

    public List<TransportMethod> findAll(){
        return transportMethodRepository.findAll();
    }

    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int proid, int qty){
        CartItem cartItem = maps.get(proid);
        cartItem.setQty(qty);
        return cartItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItem(){
        return maps.values();
    }
    @Override
    public int getCount(){
        return maps.size();
    }
    @Override
    public double getTotal(){
        return maps.values().stream().mapToDouble(item->item.getQty()* item.getPrice()).sum();
    }
}
