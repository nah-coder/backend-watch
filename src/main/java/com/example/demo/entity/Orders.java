package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class    Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDERS_DATE")
    private LocalDateTime ordersDate;

    @Column(name = "TOTAL_MONEY")
    private Double totalMoney;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "NAME_RECIVER")
    private String nameReciver;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrdersDetails> ordersDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDCUSTOMER")
    private Customer idcustomer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

}
