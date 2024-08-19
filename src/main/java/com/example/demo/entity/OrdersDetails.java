package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders_details")
public class OrdersDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idord")
    private Orders orders;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idproduct")
    private Product products;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_payment")
    private PaymentMethod payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transports")
    private TransportMethod transport;

}
