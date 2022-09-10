package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  Basket.java
 * Author:  dajan
 * Purpose: Defines the Class Basket
 ***********************************************************************/

import com.example.apotekasmilje.model.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="basket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
   @Id
   @SequenceGenerator(name = "basket_sequence_generator", sequenceName = "basket_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name="totalPrice")
   private float totalPrice;
   @OneToOne(cascade = {CascadeType.ALL})
   private AuthenticatedUser authenticatedUser;
   @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
   @JoinTable(name = "basket_product",
           joinColumns = @JoinColumn(name = "basket_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
   private List<Product> product;

}