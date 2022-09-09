package com.example.apotekasmilje.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/***********************************************************************
 * Module:  Characteristics.java
 * Author:  dajan
 * Purpose: Defines the Class Characteristics
 ***********************************************************************/

@Entity
@Table(name="characteristics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Characteristics {
   @Id
   @SequenceGenerator(name = "c_sequence_generator", sequenceName = "c_sequence", initialValue = 300)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "name",nullable = false,length = 512)
   private String name;
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="product_id")
   private Product product;
}