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
@Table(name = "transport_method")
public class TransportMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "ISACTIVE")
    private Byte isactive;

    @OneToMany(mappedBy = "idtransport",cascade = CascadeType.ALL)
    private List<Orders> orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CART_TRANSPORT")
    private CartItem cartItemTransport;
}
