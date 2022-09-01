package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  ProductType.java
 * Author:  dajan
 * Purpose: Defines the Class ProductType
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="product_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
   @Id
   @SequenceGenerator(name = "product_t_sequence_generator", sequenceName = "product_t_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_t_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "name",nullable = false)
   private String name;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}