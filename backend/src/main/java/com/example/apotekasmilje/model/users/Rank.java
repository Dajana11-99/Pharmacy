package com.example.apotekasmilje.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/***********************************************************************
 * Module:  Rank.java
 * Author:  dajan
 * Purpose: Defines the Class Rank
 ***********************************************************************/


@Entity
@Table(name="rank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rank {
   @Id
   @SequenceGenerator(name = "rank_sequence_generator", sequenceName = "rank_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name="name",nullable = false)
   private String name;
   @Column(name="points",nullable = false)
   private int points;
   @Column(name="discountPercentage",nullable = false)
   private int discountPercentage;
   @Column(name="pointsForCompletedOrder")
   private int pointsForCompletedOrder;
   @Column(name="pointsForCancelledOrders")
   private int pointsForCancelledOrders;
}