package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  ProductCategory.java
 * Author:  dajan
 * Purpose: Defines the Class ProductCategory
 ***********************************************************************/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
   private Long id;
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "description", nullable = false,length = 512)
   private String description;
   @OnDelete(action = OnDeleteAction.CASCADE)
   @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="type_id")
   private  ProductType productType;
}