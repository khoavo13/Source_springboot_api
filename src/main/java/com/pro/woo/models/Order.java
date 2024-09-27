package com.pro.woo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="fullname",length = 100)
    private String fullName;

    @Column(length = 100)
    private String email;

    @Column(name="phone_number",length = 100)
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String note;

    @Column(name="order_date")
    private Date orderDate;

    private String status;

    @Column(name="total_money")
    private Integer totalMoney;

    @Column(name="shipping_method")
    private String shippingMethod;

    @Column(name="shipping_address")
    private String shippingAddress;

    @Column(name="shipping_date")
    private LocalDate shippingDate;

    @Column(name="tracking_number")
    private String trackingNumber;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="active")
    private Boolean  active;

}



