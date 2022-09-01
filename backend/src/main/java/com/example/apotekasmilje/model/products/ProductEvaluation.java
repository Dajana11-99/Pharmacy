package com.example.apotekasmilje.model.products;

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
   @SequenceGenerator(name = "product_e_sequence_generator", sequenceName = "product_e_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_e_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "comment")
   private String comment;
   @Column(name = "grade")
   private int grade;
   @Column(name = "status",columnDefinition = "boolean default false")
   private boolean status=false;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="product_id")
   private  Product product;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="user_id")
   private AuthenticatedUser authenticatedUser;
}