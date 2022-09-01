package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  ProductCategory.java
 * Author:  dajan
 * Purpose: Defines the Class ProductCategory
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="product_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
   @Id
   @SequenceGenerator(name = "product_cat_sequence_generator", sequenceName = "product_cat_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_cat_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "description", nullable = false)
   private String description;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="type_id")
   private  ProductType productType;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}