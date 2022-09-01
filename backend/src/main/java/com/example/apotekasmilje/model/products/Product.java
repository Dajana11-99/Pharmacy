package com.example.apotekasmilje.model.products;
/***********************************************************************
 * Module:  Product.java
 * Author:  dajan
 * Purpose: Defines the Class Product
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
   @Id
   @SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "price", nullable = false)
   private float price;
   @Column(name = "expirationDate", nullable = false)
   private Date expirationDate;
   @Column(name = "quantity", nullable = false)
   private int quantity;
   @Column(name = "onSale",columnDefinition = "boolean default false")
   private boolean onSale=false;
   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinTable(name = "product_images",
         joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
         inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
   private List<Image> image;
   @OneToOne(cascade = {CascadeType.ALL})
   private ProductInformation productInformation;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="productCategory_id")
   private ProductCategory productCategory;
   @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
   private List<ProductSale> productSales;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;

}