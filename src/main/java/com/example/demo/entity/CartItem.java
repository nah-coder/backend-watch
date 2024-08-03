package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private Integer qty=1;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "cartItemTransport", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TransportMethod> transportMethods;

    @OneToMany(mappedBy = "cartItemPayment", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PaymentMethod> paymentMethods;
}
