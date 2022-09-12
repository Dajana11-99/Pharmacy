package com.example.apotekasmilje.model.order; /***********************************************************************
 * Module:  Order.java
 * Author:  dajan
 * Purpose: Defines the Class Order
 ***********************************************************************/

import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.products.BasketProducts;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
   @Id
   @SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "date")
   private LocalDate date;
   @Column(name = "totalPrice")
   private float totalPrice;
   @Column(name = "status")
   private OrderStatus status;
   @Column(name = "discountPercentage")
   private int discountPercentage;
   @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="delivery_id")
   private Delivery delivery;
   @ManyToOne( cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="payment_id")
   private Payment payment;
   @OneToMany(fetch = FetchType.EAGER,mappedBy = "order",cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   private List<OrderProducts> orderProducts= new ArrayList<>();
   @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name = "user_id")
   private AuthenticatedUser authenticatedUser;
}