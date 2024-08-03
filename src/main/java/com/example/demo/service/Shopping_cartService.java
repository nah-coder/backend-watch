package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.CartItemmm;

import java.util.Collection;

public interface Shopping_cartService {
    void add(CartItem item);

    void remove(int id);

    CartItem update(int proid, int qty);

    void clear();

    Collection<CartItem> getAllItem();

    int getCount();

    double getTotal();
}
