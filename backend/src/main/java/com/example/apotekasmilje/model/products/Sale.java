package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  Sale.java
 * Author:  dajan
 * Purpose: Defines the Class Sale
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
   @Id
   @SequenceGenerator(name = "product_t_sequence_generator", sequenceName = "product_t_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_t_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "name",nullable = false)
   private String name;
   @Column(name = "start",nullable = false)
   private Date start;
   @Column(name = "end_date",nullable = false)
   private Date end;
   @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
   private List<ProductSale> productSales;
}