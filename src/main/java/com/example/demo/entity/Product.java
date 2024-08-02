package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUATITY")
    private Integer quatity;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDCATEGORY")
    private Category category;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<OrdersDetails> ordersdetails;


}
