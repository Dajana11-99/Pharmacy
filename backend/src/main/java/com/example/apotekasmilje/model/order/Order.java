package com.example.apotekasmilje.model.order; /***********************************************************************
 * Module:  Order.java
 * Author:  dajan
 * Purpose: Defines the Class Order
 ***********************************************************************/

import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
   private int id;
   @Column(name = "date")
   private Date date;
   @Column(name = "totalPrice")
   private float totalPrice;
   @Column(name = "status")
   private OrderStatus status;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="delivery_id")
   private Delivery delivery;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="payment_id")
   private Payment payment;
   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinTable(name = "products_order",
           joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
   private List<Product> product;
}