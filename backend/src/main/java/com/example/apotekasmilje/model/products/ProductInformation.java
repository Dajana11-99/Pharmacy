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
   @SequenceGenerator(name = "product_i_sequence_generator", sequenceName = "product_i_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_i_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "description")
   private String description;
   @Column(name = "manufacturer")
   private String manufacturer;
   @Column(name = "brand")
   private String brand;
   @Column(name = "dose")
   private String dose;
   @Column(name = "ingredients")
   private String ingredients;
   @Column(name = "characteristics")
   private String characteristics;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}