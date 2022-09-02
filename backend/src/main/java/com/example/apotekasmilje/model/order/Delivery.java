package com.example.apotekasmilje.model.order; /***********************************************************************
 * Module:  Delivery.java
 * Author:  dajan
 * Purpose: Defines the Class Delivery
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="delivery")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
   @Id
   @SequenceGenerator(name = "delivery_sequence_generator", sequenceName = "delivery_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "price", nullable = false)
   private float price;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}