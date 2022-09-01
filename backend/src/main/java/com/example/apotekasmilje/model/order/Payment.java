package com.example.apotekasmilje.model.order; /***********************************************************************
 * Module:  Payment.java
 * Author:  dajan
 * Purpose: Defines the Class Payment
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
   @Id
   @SequenceGenerator(name = "payment_sequence_generator", sequenceName = "payment_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "name")
   private String name;
   @Column(name = "price")
   private float price;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}