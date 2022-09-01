package com.example.apotekasmilje.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***********************************************************************
 * Module:  MasterOfPharmacy.java
 * Author:  dajan
 * Purpose: Defines the Class MasterOfPharmacy
 ***********************************************************************/
@Entity
@DiscriminatorValue("MasterOfPharmacy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterOfPharmacy extends Person {
   public static String roleApp = "ROLE_Master_Of_Pharmacy";
   @Column(name="licenceNum")
   private String licenceNum;
}