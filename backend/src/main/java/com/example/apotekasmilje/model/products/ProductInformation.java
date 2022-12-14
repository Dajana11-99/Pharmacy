package com.example.apotekasmilje.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/***********************************************************************
 * Module:  ProductInformation.java
 * Author:  dajan
 * Purpose: Defines the Class ProductInformation
 ***********************************************************************/


@Entity
@Table(name="product_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInformation {
   @Id
   @SequenceGenerator(name = "product_i_sequence_generator", sequenceName = "product_i_sequence", initialValue = 200)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_i_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "description",length = 512)
   private String description;
   @Column(name = "manufacturer")
   private String manufacturer;
   @Column(name = "brand")
   private String brand;
   @Column(name = "dose",length = 512)
   private String dose;
   @Column(name = "ingredients",length = 512)
   private String ingredients;
   @Column(name = "characteristics",length = 512)
   private String characteristics;
}