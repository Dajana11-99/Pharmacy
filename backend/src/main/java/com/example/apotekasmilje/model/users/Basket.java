package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  Basket.java
 * Author:  dajan
 * Purpose: Defines the Class Basket
 ***********************************************************************/

import com.example.apotekasmilje.model.products.BasketProducts;
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
   @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE} )
   private AuthenticatedUser authenticatedUser;
   @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL)
   private List<BasketProducts> basketProducts= new ArrayList<>();
}