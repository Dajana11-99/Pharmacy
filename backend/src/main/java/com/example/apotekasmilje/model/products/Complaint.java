package com.example.apotekasmilje.model.products;

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/***********************************************************************
 * Module:  Complaint.java
 * Author:  dajan
 * Purpose: Defines the Class Complaint
 ***********************************************************************/

@Entity
@Table(name="complaint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
   @Id
   @SequenceGenerator(name = "complaint_sequence_generator", sequenceName = "complaint_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "reason")
   private String reason;
   @Column(name = "status",columnDefinition = "boolean default false")
   private boolean status=false;
   @ManyToOne(cascade = {CascadeType.ALL})
   private ProductSale productSale;
}