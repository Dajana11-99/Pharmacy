package com.example.apotekasmilje.model.products;

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/***********************************************************************
 * Module:  ProductEvaluation.java
 * Author:  dajan
 * Purpose: Defines the Class ProductEvaluation
 ***********************************************************************/

@Entity
@Table(name="product_evaluation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvaluation {
   @Id
   @SequenceGenerator(name = "product_e_sequence_generator", sequenceName = "product_e_sequence", initialValue = 200)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_e_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "comment",length = 512,nullable = false)
   private String comment;
   @Column(name = "grade",nullable = false)
   private int grade;
   @Column(name = "status",columnDefinition = "boolean default false")
   private boolean status=false;
   @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="product_id")
   private  Product product;
   @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="user_id")
   private AuthenticatedUser authenticatedUser;
   private LocalDate date;
}